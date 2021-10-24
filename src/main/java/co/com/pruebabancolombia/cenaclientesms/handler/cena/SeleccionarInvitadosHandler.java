package co.com.pruebabancolombia.cenaclientesms.handler.cena;

import co.com.pruebabancolombia.cenaclientesms.service.impl.cena.SeleccionarInvitadosService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SeleccionarInvitadosHandler {

    private final SeleccionarInvitadosService seleccionarInvitadosService;

    public SeleccionarInvitadosHandler(SeleccionarInvitadosService seleccionarInvitadosService) {
        this.seleccionarInvitadosService = seleccionarInvitadosService;
    }

    public String ejecutar(MultipartFile criteriosSeleccion){
        return seleccionarInvitadosService.ejecutar(criteriosSeleccion);
    }
}
