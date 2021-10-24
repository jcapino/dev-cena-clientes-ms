package co.com.pruebabancolombia.cenaclientesms.service;

import co.com.pruebabancolombia.cenaclientesms.CenaClientesMsApplicationTests;
import co.com.pruebabancolombia.cenaclientesms.service.impl.cena.DesencriptarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {CenaClientesMsApplicationTests.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DesencriptarServiceTest {

    @Autowired
    DesencriptarService desencriptarService;

    @Test
    @DisplayName("Debe desencriptar un código de usuario")
    void debeDesencriptarUnCodigoDeUsuario(){
        String codigoEncriptado = "QzEwMTA0";
        String codigoEsperado = "C10104";
        String codigoObtenido = desencriptarService.ejecutar(codigoEncriptado);

        Assertions.assertEquals(codigoEsperado, codigoObtenido);
    }
}
