package co.com.pruebabancolombia.cenaclientesms.controller.cena;

import co.com.pruebabancolombia.cenaclientesms.dto.DtoInvitado;
import co.com.pruebabancolombia.cenaclientesms.handler.cena.SeleccionarInvitadosHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invitados")
@Api(tags = "Controlador para seleccionar los invitados a la cena para clientes especiales")
public class SeleccionarInvitadosController {

    private final SeleccionarInvitadosHandler seleccionarInvitadosHandler;

    public SeleccionarInvitadosController(SeleccionarInvitadosHandler seleccionarInvitadosHandler) {
        this.seleccionarInvitadosHandler = seleccionarInvitadosHandler;
    }

    @GetMapping
    @ApiOperation(value = "Servicio que obtiene los invitados a la cena de clientes de acuerdo a los criterios de selección (archivo.txt)")
    public ResponseEntity<String> seleccionarInvitados(@RequestParam("criteriosSeleccion") MultipartFile criteriosSeleccion) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(seleccionarInvitadosHandler.ejecutar(criteriosSeleccion));
    }
}
