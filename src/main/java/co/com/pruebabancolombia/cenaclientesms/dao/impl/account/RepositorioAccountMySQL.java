package co.com.pruebabancolombia.cenaclientesms.dao.impl.account;

import co.com.pruebabancolombia.cenaclientesms.dao.declaration.RepositorioAccount;
import co.com.pruebabancolombia.cenaclientesms.model.account.Account;
import co.com.pruebabancolombia.cenaclientesms.utils.sqlstatement.SqlStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class RepositorioAccountMySQL implements RepositorioAccount {

    @SqlStatement(namespace = "account", value = "obtenerCuentasXClientId")
    private String sqlObtenerClienteXId;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RepositorioAccountMySQL(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> obtenerCuentasXClientId(Integer clientId) {
        try {
            SqlParameterSource parametros = new MapSqlParameterSource()
                    .addValue("client_id", clientId);
            return jdbcTemplate.query(sqlObtenerClienteXId, parametros, new MapperSQLAccount());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }
}
