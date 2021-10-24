package co.com.pruebabancolombia.cenaclientesms.dao.impl.account;

import co.com.pruebabancolombia.cenaclientesms.model.account.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperSQLAccount implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        var id = rs.getInt("id");
        var clientId = rs.getInt("client_id");
        var balance = rs.getDouble("balance");

        return new Account(
                id,
                clientId,
                balance
        );
    }
}
