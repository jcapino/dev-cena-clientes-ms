package co.com.pruebabancolombia.cenaclientesms.utils.exception;

/**
 * Excepción que se puede presentar al procesar los archivos SQL
 */
public class StatementException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public StatementException(String message) {
        super(message);
    }

}
