package co.com.pruebabancolombia.cenaclientesms.dao.impl.cliente;

import co.com.pruebabancolombia.cenaclientesms.dao.declaration.RepositorioClient;
import co.com.pruebabancolombia.cenaclientesms.dao.impl.account.MapperSQLAccount;
import co.com.pruebabancolombia.cenaclientesms.model.client.Client;
import co.com.pruebabancolombia.cenaclientesms.model.invitado.CriteriosSeleccion;
import co.com.pruebabancolombia.cenaclientesms.utils.sqlstatement.SqlStatement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class RepositorioClientMySQL implements RepositorioClient {

    @SqlStatement(namespace = "clientes", value = "obtenerClientesXCriterioSeleccion")
    private String sqlObtenerClientesXCriterioSeleccion;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RepositorioClientMySQL(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Client> obtenerClientesXCriterioSeleccion(CriteriosSeleccion criteriosSeleccion) {
        try{
            SqlParameterSource parametros = new MapSqlParameterSource()
                    .addValue("type", criteriosSeleccion.getTipoCliente())
                    .addValue("location", criteriosSeleccion.getCdUbicacionGeografica())
                    .addValue("rangoInicial", criteriosSeleccion.getRangoInicialBalance())
                    .addValue("rangoFinal", criteriosSeleccion.getRangoFinalBalance());

            return jdbcTemplate.query(sqlObtenerClientesXCriterioSeleccion, parametros, new MapperSQLClient());
        }catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return null;
        }
    }


}
