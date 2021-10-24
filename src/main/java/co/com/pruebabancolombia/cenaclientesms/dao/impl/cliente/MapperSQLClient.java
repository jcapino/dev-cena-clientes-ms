package co.com.pruebabancolombia.cenaclientesms.dao.impl.cliente;

import co.com.pruebabancolombia.cenaclientesms.model.client.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class MapperSQLClient implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        var id = rs.getInt("id");
        var code = rs.getString("code");
        var male = rs.getInt("male");
        var type = rs.getInt("type");
        var location = rs.getString("location");
        var company = rs.getString("company");
        var encrypt = rs.getInt("encrypt");
        var balance = obtenerBalance(rs);
        Client cliente = new Client(
                id,
                code,
                male,
                type,
                location,
                company,
                encrypt
        );

        cliente.setBalance(balance);

        return cliente;
    }

    public Double obtenerBalance(ResultSet rs){
        try{
            return rs.getDouble("balance");
        }catch (Exception ex){
            log.error("Error: Balance no está implementado en SELECT");
            return null;
        }
    }
}
