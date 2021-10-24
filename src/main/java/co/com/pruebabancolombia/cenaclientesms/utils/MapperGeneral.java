package co.com.pruebabancolombia.cenaclientesms.utils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Interfaz con la definiciÓn para los mappers de DTO y Clases de dominio(modelos)
 * @author Juan Camilo Campo Pino - jcamilopino@gmail.com
 * @date 22/10/2021
 */
public interface MapperGeneral <D, E>{
    E dtoAModelo(D dto);

    D modeloADto(E modelo);

    default List<E> listaDtoAListaModelo(List<D> listaDto) {
        return listaDto.stream().map(this::dtoAModelo).collect(Collectors.toList());
    }

    default List<D> listaModeloAListaDto(List<E> listaModelo) {
        return listaModelo.stream().map(this::modeloADto).collect(Collectors.toList());
    }
}
