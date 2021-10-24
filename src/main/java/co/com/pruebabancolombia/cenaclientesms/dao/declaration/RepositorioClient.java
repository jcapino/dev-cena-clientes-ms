package co.com.pruebabancolombia.cenaclientesms.dao.declaration;

import co.com.pruebabancolombia.cenaclientesms.model.client.Client;
import co.com.pruebabancolombia.cenaclientesms.model.invitado.CriteriosSeleccion;

import java.util.List;

public interface RepositorioClient {
    List<Client> obtenerClientesXCriterioSeleccion(CriteriosSeleccion criteriosSeleccion);
}
