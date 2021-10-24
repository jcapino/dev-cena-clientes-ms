package co.com.pruebabancolombia.cenaclientesms.service.impl.cena;

import co.com.pruebabancolombia.cenaclientesms.model.invitado.CriteriosSeleccion;
import co.com.pruebabancolombia.cenaclientesms.utils.exception.ExcepcionValidacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CriteriosSeleccionService {

    private final String FORMATO_ENTRADA_VALIDO = ".TXT";
    private final String KEY_MESA = "<";
    private final String KEY_TIPO_CLIENTE = "TC";
    private final String KEY_UBICACION_GEOGRAFICA = "UG";
    private final String KEY_RANGO_INICIAL = "RI";
    private final String KEY_RANGO_FINAL = "RF";

    public List<CriteriosSeleccion> ejecutar(MultipartFile criteriosSeleccion) {

        validarFormatoArchivo(criteriosSeleccion);

        List<CriteriosSeleccion> listaCriteriosSeleccion = prepararListaCriteriosSeleccion(criteriosSeleccion);

        if (listaCriteriosSeleccion.isEmpty()) {
            throw new ExcepcionValidacion("No hay criterios de selección, por favor revise el archivo y vuelva a cargarlo");
        }

        return listaCriteriosSeleccion;
    }

    private void validarFormatoArchivo(MultipartFile criteriosSeleccion) {

        if (criteriosSeleccion == null) {
            throw new ExcepcionValidacion("Error: No ha cargado el archivo de criterios de selección");
        }

        if (Boolean.FALSE.equals(criteriosSeleccion.getOriginalFilename().toUpperCase().endsWith(FORMATO_ENTRADA_VALIDO))) {
            throw new ExcepcionValidacion("Formato archivo inválido");
        }
    }

    private List<CriteriosSeleccion> prepararListaCriteriosSeleccion(MultipartFile criteriosSeleccion) {
        String datosCriteriosSeleccion = obtenerDatosDeArchivo(criteriosSeleccion);

        if (datosCriteriosSeleccion == null) {
            throw new ExcepcionValidacion("Ocurrió un error al intentar obtener los datos del archivo, por favor revise el log de errores, corrija y vuelva a cargar");
        }
        return obtenerListaCriteriosSeleccion(datosCriteriosSeleccion);
    }

    private String obtenerDatosDeArchivo(MultipartFile criteriosSeleccion) {

        try {
            StringBuilder resultStringBuilder = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(criteriosSeleccion.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }
            return resultStringBuilder.toString();
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    private List<CriteriosSeleccion> obtenerListaCriteriosSeleccion(String datosCriteriosSeleccion) {
        var ref = new Object() {
            CriteriosSeleccion criterioSeleccion = new CriteriosSeleccion();
        };
        List<CriteriosSeleccion> listaCriteriosSeleccion = new ArrayList<>();
        datosCriteriosSeleccion.lines().forEach(linea -> {

            if (linea.startsWith(KEY_MESA)) {
                if (ref.criterioSeleccion.getNombreMesa() != null) {

                    if(ref.criterioSeleccion.validarDatosCriteriosSeleccion()){
                        listaCriteriosSeleccion.add(ref.criterioSeleccion);
                    }else{
                        throw new ExcepcionValidacion("La mesa " +ref.criterioSeleccion.getNombreMesa()+ " debe tener al menos un (1) criterio de selección" );
                    }

                    ref.criterioSeleccion = new CriteriosSeleccion();
                }
                ref.criterioSeleccion.setNombreMesa(linea);

            } else {
                var arrayLinea = linea.split(":");
                switch (arrayLinea[0].trim()) {
                    case KEY_TIPO_CLIENTE -> {
                        ref.criterioSeleccion.setTipoCliente(
                                Integer.parseInt(arrayLinea[1].trim())
                        );
                    }
                    case KEY_UBICACION_GEOGRAFICA -> {
                        ref.criterioSeleccion.setCdUbicacionGeografica(
                                Integer.parseInt(arrayLinea[1].trim())
                        );
                    }
                    case KEY_RANGO_INICIAL -> {
                        ref.criterioSeleccion.setRangoInicialBalance(
                                Double.parseDouble(arrayLinea[1].trim())
                        );
                    }
                    case KEY_RANGO_FINAL -> {
                        ref.criterioSeleccion.setRangoFinalBalance(
                                Double.parseDouble(arrayLinea[1].trim())
                        );
                    }
                    default -> {
                        throw new ExcepcionValidacion("El archivo contiene un valor " + linea + " no parametrizado, revise y vuelva a cargar.");
                    }
                }
            }
        });
        listaCriteriosSeleccion.add(ref.criterioSeleccion);
        return listaCriteriosSeleccion;
    }
}
