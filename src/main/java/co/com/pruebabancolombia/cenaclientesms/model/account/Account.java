package co.com.pruebabancolombia.cenaclientesms.model.account;

import co.com.pruebabancolombia.cenaclientesms.utils.validador.ValidadorArgumento;

/**
 * Clase que almacena la información de un cliente
 * @date 22/10/2021
 */
public class Account {
    private Integer id;
    private Integer clientId;
    private Double balance;

    public Account(Integer id, Integer clientId, Double balance) {

        ValidadorArgumento.validarObligatorio(id, "Id");
        ValidadorArgumento.validarObligatorio(clientId, "Código Cliente");
        ValidadorArgumento.validarObligatorio(balance, "Genero");

        ValidadorArgumento.validarEspaciosVacios(String.valueOf(id), "Código Cliente");
        ValidadorArgumento.validarEspaciosVacios(String.valueOf(clientId), "Ubicación");
        ValidadorArgumento.validarEspaciosVacios(String.valueOf(balance), "Compañía");
        this.id = id;
        this.clientId = clientId;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
