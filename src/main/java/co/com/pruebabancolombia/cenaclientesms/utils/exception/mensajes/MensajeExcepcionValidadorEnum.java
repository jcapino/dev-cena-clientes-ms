package co.com.pruebabancolombia.cenaclientesms.utils.exception.mensajes;

public enum MensajeExcepcionValidadorEnum {
    CAMPO_VACIO("El campo %s no puede ser nulo o vacío"),
    OBLIGATORIO("El campo %s es obligatorio");

    private final String mensaje;

    MensajeExcepcionValidadorEnum(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
