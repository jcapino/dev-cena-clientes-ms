package co.com.pruebabancolombia.cenaclientesms.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuración de Jasypt
 * @author Juan Camilo Campo Pino - jcamilopino@gmail.com
 * @date 22/10/2021
 */
@Configuration
public class JasyptConfig {
    public static SimpleStringPBEConfig getSimpleStringPBEConfig() {
        final var pbeConfig = new SimpleStringPBEConfig();

        //pbeConfig.setPassword(System.getProperty("jasypt.encryptor.password"));
        pbeConfig.setPassword("3jDOP2%12rv21");
        pbeConfig.setAlgorithm("PBEWithMD5AndDES");
        pbeConfig.setKeyObtentionIterations("1000");
        pbeConfig.setPoolSize("1");
        pbeConfig.setProviderName("SunJCE");
        pbeConfig.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        pbeConfig.setStringOutputType("base64");

        return pbeConfig;
    }

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor encryptor() {
        final var pbeStringEncryptor = new PooledPBEStringEncryptor();
        pbeStringEncryptor.setConfig(getSimpleStringPBEConfig());
        return pbeStringEncryptor;
    }
}
