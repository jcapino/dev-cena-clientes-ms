package co.com.pruebabancolombia.cenaclientesms.service.impl.cena;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@Service
public class DesencriptarService {

    @Value("${url.desencriptar}")
    private String urlDesencriptar;

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public String ejecutar(String valorDesencriptar){
        String url = String.format(urlDesencriptar, valorDesencriptar);
        return Objects.requireNonNull(restTemplate().getForObject(url, String.class)).replace("\"","");
    }
}
