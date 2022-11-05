package cl.cclh.demo.apirestcliente;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("cl.cclh.demo.apirestcliente.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				;
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"API REST CLIENTE",
				"Demo en API Rest en Spring para objeto cliente",
				"1.0",
				"",
				new Contact("Luis Oliveros", "", "contacto.luisoliveros@gmail.com"),
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0",
				Collections.emptyList()
				);
	}
}
