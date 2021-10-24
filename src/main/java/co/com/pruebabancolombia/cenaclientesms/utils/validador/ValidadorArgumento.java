package co.com.pruebabancolombia.cenaclientesms.utils.validador;

import co.com.pruebabancolombia.cenaclientesms.utils.exception.ExcepcionValidacion;
import co.com.pruebabancolombia.cenaclientesms.utils.exception.mensajes.MensajeExcepcionValidadorEnum;

/**
 * Clase para validar los argumentos de las clases de dominio(modelos)
 * @author Juan Camilo Campo Pino - jcamilopino@gmail.com
 * @date 23/10/2021
 */
public class ValidadorArgumento {

    public ValidadorArgumento() {
    }

    public static void validarObligatorio(Object valor, String campo){
        if(valor == null){
            throw new ExcepcionValidacion(MensajeExcepcionValidadorEnum.OBLIGATORIO, campo);
        }
    }

    public static void validarEspaciosVacios(String valor, String campo){
        if(valor.trim().isEmpty() || valor.trim().isBlank()){
            throw new ExcepcionValidacion(MensajeExcepcionValidadorEnum.OBLIGATORIO, campo);
        }
    }
}
