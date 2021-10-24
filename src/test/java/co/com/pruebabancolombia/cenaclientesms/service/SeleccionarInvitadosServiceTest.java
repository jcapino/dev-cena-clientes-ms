package co.com.pruebabancolombia.cenaclientesms.service;

import co.com.pruebabancolombia.cenaclientesms.CenaClientesMsApplicationTests;
import co.com.pruebabancolombia.cenaclientesms.model.invitado.CriteriosSeleccion;
import co.com.pruebabancolombia.cenaclientesms.service.impl.cena.CriteriosSeleccionService;
import co.com.pruebabancolombia.cenaclientesms.service.impl.cena.SeleccionarInvitadosService;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest(classes = {CenaClientesMsApplicationTests.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SeleccionarInvitadosServiceTest {

    @Autowired
    SeleccionarInvitadosService seleccionarInvitadosService;

    @Test
    void debeSeleccionarLosClientesInvitadosYDebeCoincidirConLaSalida() throws IOException {
        File file = new File("src/test/java/resources/entrada.txt");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));

        String invitados = seleccionarInvitadosService.ejecutar(multipartFile);

        Assertions.assertNotNull(invitados);
        Assertions.assertEquals(obtenerResultadoEsperado(), invitados);
    }

    public String obtenerResultadoEsperado(){
        return "<General>\n" +
                "CANCELADA\n" +
                "<Mesa 1>\n" +
                "C10078,C10086,C10025,C10089,C10190,C10191,C10043,C10104\n" +
                "<Mesa 2>\n" +
                "C10144,C10070,C10076,C10134,C10151,C10090\n" +
                "<Mesa 3>\n" +
                "CANCELADA\n" +
                "<Mesa 4>\n" +
                "CANCELADA\n" +
                "<Mesa 5>\n" +
                "C10201,C10202,C10203,C10204,C10205,C10206,C10207,C10208\n" +
                "<Mesa 6>\n" +
                "CANCELADA\n";
    }
}
