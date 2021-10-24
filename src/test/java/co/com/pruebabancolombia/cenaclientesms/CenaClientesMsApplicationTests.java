package co.com.pruebabancolombia.cenaclientesms;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@ComponentScan("co.com.pruebabancolombia")
@Profile("test")
@EnableTransactionManagement
public class CenaClientesMsApplicationTests {

	@Bean
	public DataSource h2DataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean(initMethod = "migrate")
	public Flyway flyway(DataSource dataSource) {
		Flyway.configure().locations("filesystem:src/main/resources", "filesystem:src/test/resources")
                .baselineOnMigrate(true).dataSource(dataSource).load().clean();
		return Flyway.configure().locations("filesystem:src/main/resources", "filesystem:src/test/resources")
				.baselineOnMigrate(true).dataSource(dataSource).load();
	}

	@Bean
	public NamedParameterJdbcTemplate applicationDataConnection(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
