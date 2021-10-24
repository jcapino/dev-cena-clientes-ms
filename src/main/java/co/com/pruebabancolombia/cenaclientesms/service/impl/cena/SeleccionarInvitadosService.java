package co.com.pruebabancolombia.cenaclientesms.service.impl.cena;

import co.com.pruebabancolombia.cenaclientesms.dao.declaration.RepositorioClient;
import co.com.pruebabancolombia.cenaclientesms.model.client.Client;
import co.com.pruebabancolombia.cenaclientesms.model.invitado.CriteriosSeleccion;
import co.com.pruebabancolombia.cenaclientesms.model.invitado.EstadoGrupo;
import co.com.pruebabancolombia.cenaclientesms.model.invitado.Grupo;
import co.com.pruebabancolombia.cenaclientesms.utils.exception.ExcepcionValidacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Slf4j
public class SeleccionarInvitadosService {

    private final CriteriosSeleccionService criteriosSeleccionService;
    private final DesencriptarService desencriptarService;
    private final RepositorioClient repositorioClient;
    private final int NUMERO_CLIENTES_MAX_X_MESA = 8;
    private final int NUMERO_CLIENTES_MIN_X_MESA = 4;
    private final int MAX_CLIENTES_MASCULINO_X_MESA = 4;
    private final int MAX_CLIENTES_FEMENINO_X_MESA = 4;
    private final int FEMENINO = 0;
    private final int MASCULINO = 1;

    public SeleccionarInvitadosService(CriteriosSeleccionService criteriosSeleccionService, DesencriptarService desencriptarService, RepositorioClient repositorioClient) {
        this.criteriosSeleccionService = criteriosSeleccionService;
        this.desencriptarService = desencriptarService;
        this.repositorioClient = repositorioClient;
    }

    public String ejecutar(MultipartFile archivoCriterios) {
        log.info("Iniciando proceso: Seleccionar Invitados ");

        List<CriteriosSeleccion> listaCriteriosSeleccion = criteriosSeleccionService.ejecutar(archivoCriterios);
        List<Grupo> gruposPreseleccionados = obtenerGruposPreseleccionados(listaCriteriosSeleccion);
        List<Grupo> gruposInvitados = validarInvitacionesGrupos(gruposPreseleccionados);
        StringBuilder invitados = obtenerInvitados(gruposInvitados);
        log.info("Finaliza proceso: Seleccionar Invitados ");
        return invitados.toString();
    }

    private List<Grupo> obtenerGruposPreseleccionados(List<CriteriosSeleccion> listaCriteriosSeleccion) {

        List<Grupo> listaGrupos = new ArrayList<>();

        listaCriteriosSeleccion.stream()
                .forEach(criteriosSeleccion -> {
                    List<Client> clientes = repositorioClient.obtenerClientesXCriterioSeleccion(criteriosSeleccion);

                    if (Objects.isNull(clientes)) {
                        throw new ExcepcionValidacion("Ocurrio un error consultando los clientes x criterio de selección. Por favor verifique el log de errores");
                    }

                    listaGrupos.add(new Grupo(criteriosSeleccion.getNombreMesa(), clientes, EstadoGrupo.SIN_VALIDAR));
                });
        return listaGrupos;
    }

    private List<Grupo> validarInvitacionesGrupos(List<Grupo> gruposPreseleccionados) {
        return gruposPreseleccionados.stream()
                .map(grupoPreseleccionado -> {
                    var clientes = validarYObtenerClientesInvitados(grupoPreseleccionado.getClientes());
                    grupoPreseleccionado.setClientes(clientes);

                    if (grupoPreseleccionado.getClientes().size() >= NUMERO_CLIENTES_MIN_X_MESA) {
                        grupoPreseleccionado.setEstado(EstadoGrupo.INVITADO);
                    } else {
                        grupoPreseleccionado.setEstado(EstadoGrupo.CANCELADA);
                    }
                    return grupoPreseleccionado;
                }).toList();
    }

    private List<Client> validarYObtenerClientesInvitados(List<Client> clientesPreseleccionados) {
        List<Client> clientesSeleccionados = new ArrayList<>();

        clientesPreseleccionados.stream()
                .forEach(cliente -> {
                    boolean existeRepresentanteCompania = existeCompaniaClientesPreseleccionados(clientesSeleccionados, cliente);
                    if (existeRepresentanteCompania) {
                        return; //No agrega al cliente porque ya existe otro de la misma compañia
                    }
                    int numClientesXGeneroM = obtenerNroClientesSeleccionadosXGenero(clientesSeleccionados, MASCULINO);
                    int numClientesXGeneroF = obtenerNroClientesSeleccionadosXGenero(clientesSeleccionados, FEMENINO);

                    if (clientesSeleccionados.size() < NUMERO_CLIENTES_MAX_X_MESA) {
                        if (cliente.getMale().equals(MASCULINO) && numClientesXGeneroM < MAX_CLIENTES_MASCULINO_X_MESA) {
                            clientesSeleccionados.add(cliente);
                        } else if (cliente.getMale().equals(FEMENINO) && numClientesXGeneroF < MAX_CLIENTES_FEMENINO_X_MESA) {
                            clientesSeleccionados.add(cliente);
                        }
                    }
                });

        List<Client> clientesInvitados = clientesSeleccionados;
        if (clientesInvitados.size() < NUMERO_CLIENTES_MAX_X_MESA) {

            int numClientesXGeneroM = obtenerNroClientesSeleccionadosXGenero(clientesInvitados, MASCULINO);
            int numClientesXGeneroF = obtenerNroClientesSeleccionadosXGenero(clientesInvitados, FEMENINO);

            if (numClientesXGeneroM > numClientesXGeneroF) {
                clientesInvitados = eliminarEIgualarCantidadClientesSeleccionadosXGenero(clientesSeleccionados, numClientesXGeneroF);
            } else if (numClientesXGeneroM < numClientesXGeneroF) {
                clientesInvitados = eliminarEIgualarCantidadClientesSeleccionadosXGenero(clientesSeleccionados, numClientesXGeneroM);
            }
        }

        return clientesInvitados;
    }

    private boolean existeCompaniaClientesPreseleccionados(List<Client> clientesGrupoPreseleccionado, Client cliente) {
        return clientesGrupoPreseleccionado.stream().parallel()
                .anyMatch(clienteGrupo -> Objects.equals(clienteGrupo.getCompany(), cliente.getCompany()));
    }

    private Integer obtenerNroClientesSeleccionadosXGenero(List<Client> clientesGrupoPreseleccionado, Integer genero) {
        return clientesGrupoPreseleccionado.stream().parallel()
                .filter(client -> Objects.equals(genero, client.getMale()))
                .toList()
                .size();
    }

    private List<Client> eliminarEIgualarCantidadClientesSeleccionadosXGenero(List<Client> clientesSeleccionados, Integer numClientexGenero) {

        List<Client> clientesInvitados = new ArrayList<>();
        List<Client> clientes = clientesSeleccionados.stream()
                .filter(cliente -> cliente.getMale().equals(MASCULINO))
                .toList();
        List<Client> clientesMasculinos = new ArrayList<>(clientes);

        if (clientesMasculinos.size() > 1) {
            clientesMasculinos.sort(Comparator.comparing(Client::getBalance).reversed());
        }
        var ref = new Object() {
            int contador = 1;
        };
        clientesMasculinos.stream().forEach(cliente -> {
            if (ref.contador <= numClientexGenero) {
                clientesInvitados.add(cliente);
                ref.contador++;
            }
        });

        clientes = clientesSeleccionados.stream()
                .filter(cliente -> cliente.getMale().equals(FEMENINO))
                .toList();
        List<Client> clientesFemeninos = new ArrayList<>(clientes);
        if (clientesFemeninos.size() > 1) {
            clientesFemeninos.sort(Comparator.comparing(Client::getBalance).reversed());
        }

        ref.contador = 1;
        clientesFemeninos.stream().forEach(cliente -> {
            if (ref.contador <= numClientexGenero) {
                clientesInvitados.add(cliente);
                ref.contador++;
            }
        });
        clientesInvitados.sort(Comparator.comparing(Client::getBalance).reversed());
        return clientesInvitados;
    }

    private StringBuilder obtenerInvitados(List<Grupo> listaGruposInvitados) {

        StringBuilder resultStringBuilder = new StringBuilder();
        listaGruposInvitados.stream().forEach(grupo -> {

            resultStringBuilder.append(grupo.getNombreMesa()).append("\n");
            if (EstadoGrupo.CANCELADA.equals(grupo.getEstado())) {
                resultStringBuilder.append(grupo.getEstado().name());
            } else {

                grupo.getClientes().stream().forEach(cliente -> {
                    if (cliente.getEncrypt() == 1) {
                        resultStringBuilder.append(
                                desencriptarService.ejecutar(cliente.getCode())
                        ).append(",");
                    } else {
                        resultStringBuilder.append(cliente.getCode()).append(",");
                    }
                });
                resultStringBuilder.deleteCharAt(resultStringBuilder.lastIndexOf(","));
            }
            resultStringBuilder.append("\n");
        });
        return resultStringBuilder;
    }
}
