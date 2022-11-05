package cl.cclh.demo.apirestcliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ApiRestClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestClienteApplication.class, args);
	}

}
