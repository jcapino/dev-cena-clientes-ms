package co.com.pruebabancolombia.cenaclientesms.utils.exception;

import co.com.pruebabancolombia.cenaclientesms.utils.exception.mensajes.MensajeExcepcionValidadorEnum;

public class ExcepcionValidacion extends RuntimeException{
    private static final long serialVersionUID = -400771058L;

    public ExcepcionValidacion(String mensaje){
        super(mensaje);
    }

    public ExcepcionValidacion(MensajeExcepcionValidadorEnum mensaje, String campo){
        super(String.format(mensaje.getMensaje(), campo));
    }
}
