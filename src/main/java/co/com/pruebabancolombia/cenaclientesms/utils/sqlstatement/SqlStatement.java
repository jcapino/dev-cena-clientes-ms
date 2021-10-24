package co.com.pruebabancolombia.cenaclientesms.utils.sqlstatement;

import java.lang.annotation.*;

/**
 * Anotación para declarar las consultas SQL
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
public @interface SqlStatement {
    String value() default "";
    String namespace() default "";
}
