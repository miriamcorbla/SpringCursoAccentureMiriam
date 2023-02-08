package es.rf.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//exclude = {DataSourceAutoConfiguration.class}

@SpringBootApplication(scanBasePackages = "es.rf.tienda")
public class TiendaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaSpringApplication.class, args);
	}

}
