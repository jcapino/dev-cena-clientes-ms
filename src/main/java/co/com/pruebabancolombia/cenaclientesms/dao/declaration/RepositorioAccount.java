package co.com.pruebabancolombia.cenaclientesms.dao.declaration;

import co.com.pruebabancolombia.cenaclientesms.model.account.Account;

import java.util.List;

public interface RepositorioAccount {

    List<Account> obtenerCuentasXClientId(Integer clientId);
}
