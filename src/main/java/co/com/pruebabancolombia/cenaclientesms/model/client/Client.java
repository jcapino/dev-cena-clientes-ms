package co.com.pruebabancolombia.cenaclientesms.model.client;

import co.com.pruebabancolombia.cenaclientesms.model.account.Account;
import co.com.pruebabancolombia.cenaclientesms.utils.validador.ValidadorArgumento;

import java.util.List;

/**
 * Clase que almacena la información de un cliente
 * @date 22/10/2021
 */
public class Client {
    private Integer id;
    private String code;
    private Integer male;
    private Integer type;
    private String location;
    private String company;
    private Integer encrypt;
    private Double balance;
    private List<Account> cuentas;

    public Client(Integer id, String code, Integer male, Integer type, String location, String company, Integer encrypt) {
        ValidadorArgumento.validarObligatorio(id, "Id");
        ValidadorArgumento.validarObligatorio(code, "Código Cliente");
        ValidadorArgumento.validarObligatorio(male, "Genero");
        ValidadorArgumento.validarObligatorio(type, "Tipo");
        ValidadorArgumento.validarObligatorio(location, "Ubicación");
        ValidadorArgumento.validarObligatorio(company, "Compañía");
        ValidadorArgumento.validarObligatorio(encrypt, "Encriptar Cuenta");

        ValidadorArgumento.validarEspaciosVacios(String.valueOf(id), "Id");
        ValidadorArgumento.validarEspaciosVacios(code, "Código Cliente");
        ValidadorArgumento.validarEspaciosVacios(String.valueOf(male), "Genero");
        ValidadorArgumento.validarEspaciosVacios(String.valueOf(type), "Tipo");
        ValidadorArgumento.validarEspaciosVacios(location, "Ubicación");
        ValidadorArgumento.validarEspaciosVacios(company, "Compañía");
        ValidadorArgumento.validarEspaciosVacios(String.valueOf(encrypt), "Encriptar Cuenta");
        this.id = id;
        this.code = code;
        this.male = male;
        this.type = type;
        this.location = location;
        this.company = company;
        this.encrypt = encrypt;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Integer getMale() {
        return male;
    }

    public Integer getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getCompany() {
        return company;
    }

    public Integer getEncrypt() {
        return encrypt;
    }

    public List<Account> getCuentas() {
        return cuentas;
    }

    public Double getBalance(){
        return balance;
    }

    public void setCuentas(List<Account> cuentas) {
        this.cuentas = cuentas;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
