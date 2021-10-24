package co.com.pruebabancolombia.cenaclientesms.model.invitado;

import co.com.pruebabancolombia.cenaclientesms.model.client.Client;

import java.util.List;

/**
 * Clase que almacena la información de los grupos
 * @date 23/10/2021
 */
public class Grupo {
    private String nombreMesa;
    private List<Client> clientes;
    private Double balanceTotal;
    private EstadoGrupo estado;

    public Grupo(String nombreMesa, List<Client> clientes, EstadoGrupo estado) {
        this.nombreMesa = nombreMesa;
        this.clientes = clientes;
        this.estado = estado;
    }

    public String getNombreMesa() {
        return nombreMesa;
    }

    public List<Client> getClientes() {
        return clientes;
    }

    public void setClientes(List<Client> clientes) {
        this.clientes = clientes;
    }

    public EstadoGrupo getEstado() {
        return estado;
    }

    public void setEstado(EstadoGrupo estado) {
        this.estado = estado;
    }

    public Double getBalanceTotal() {
        return balanceTotal;
    }

    public void setBalanceTotal(Double balanceTotal) {
        this.balanceTotal = balanceTotal;
    }
}
