package co.com.pruebabancolombia.cenaclientesms.model.invitado;

import co.com.pruebabancolombia.cenaclientesms.utils.validador.ValidadorArgumento;

/**
 * Clase que almacena la información de los criterios de selección de invitados a la cena
 *
 * @date 23/10/2021
 */
public class CriteriosSeleccion {
    private String nombreMesa;
    private Integer tipoCliente;
    private Integer cdUbicacionGeografica;
    private Double rangoInicialBalance;
    private Double rangoFinalBalance;

    public CriteriosSeleccion() {
        /*
        ValidadorArgumento.validarObligatorio(nombreMesa, "Nombre Mesa");
        ValidadorArgumento.validarObligatorio(tipoCliente, "Tipo Cliente");
        ValidadorArgumento.validarObligatorio(cdUbicacionGeografica, "Ubicación Geográfica");
        ValidadorArgumento.validarObligatorio(rangoInicialBalance, "Rango Inicial Balance");

        ValidadorArgumento.validarEspaciosVacios(nombreMesa, "Nombre Mesa");
        ValidadorArgumento.validarEspaciosVacios(tipoCliente, "Tipo Cliente");
        ValidadorArgumento.validarEspaciosVacios(String.valueOf(cdUbicacionGeografica), "Ubicación geográfica");
        ValidadorArgumento.validarEspaciosVacios(String.valueOf(rangoInicialBalance), "Rango Inicial Balance");

        this.nombreMesa = nombreMesa;
        this.tipoCliente = tipoCliente;
        this.cdUbicacionGeografica = cdUbicacionGeografica;
        this.rangoInicialBalance = rangoInicialBalance;*/
    }

    public String getNombreMesa() {
        return nombreMesa;
    }

    public Integer getTipoCliente() {
        return tipoCliente;
    }

    public Integer getCdUbicacionGeografica() {
        return cdUbicacionGeografica;
    }

    public Double getRangoInicialBalance() {
        return rangoInicialBalance;
    }

    public Double getRangoFinalBalance() {
        return rangoFinalBalance;
    }

    public void setNombreMesa(String nombreMesa) {
        this.nombreMesa = nombreMesa;
    }

    public void setTipoCliente(Integer tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setCdUbicacionGeografica(Integer cdUbicacionGeografica) {
        this.cdUbicacionGeografica = cdUbicacionGeografica;
    }

    public void setRangoInicialBalance(Double rangoInicialBalance) {
        this.rangoInicialBalance = rangoInicialBalance;
    }

    public void setRangoFinalBalance(Double rangoFinalBalance) {
        /*
        ValidadorArgumento.validarObligatorio(rangoFinalBalance, "Rango Final Balance");
        ValidadorArgumento.validarEspaciosVacios(String.valueOf(rangoFinalBalance), "Rango Final Balance");
        */
        this.rangoFinalBalance = rangoFinalBalance;
    }

    public boolean validarDatosCriteriosSeleccion() {
        return
                (nombreMesa != null && Boolean.FALSE.equals(nombreMesa.isEmpty()))
                        && (tipoCliente != null || cdUbicacionGeografica != null || rangoInicialBalance != null || rangoFinalBalance != null);
    }
}
