package co.com.pruebabancolombia.cenaclientesms.service;

import co.com.pruebabancolombia.cenaclientesms.CenaClientesMsApplicationTests;
import co.com.pruebabancolombia.cenaclientesms.model.invitado.CriteriosSeleccion;
import co.com.pruebabancolombia.cenaclientesms.service.impl.cena.CriteriosSeleccionService;
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
import java.util.List;

@SpringBootTest(classes = {CenaClientesMsApplicationTests.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CriteriosSeleccionServiceTest {

    @Autowired
    CriteriosSeleccionService criteriosSeleccionService;

    @Test
    void debeProcesarArchivoTxtConEntradaDatos() throws IOException {
        File file = new File("src/test/java/resources/entrada.txt");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));

        List<CriteriosSeleccion> criteriosSeleccion= criteriosSeleccionService.ejecutar(multipartFile);

        Assertions.assertNotNull(criteriosSeleccion);
        Assertions.assertEquals(7, criteriosSeleccion.size());
    }
}
