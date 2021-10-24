package co.com.pruebabancolombia.cenaclientesms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoHttpErrorResponse {
    private String mensaje;
    private Integer codigoError;

    public DtoHttpErrorResponse(String mensaje, Integer codigoError) {
        this.mensaje = mensaje;
        this.codigoError = codigoError;
    }
}
