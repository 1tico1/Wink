package br.com.WellDone.Wink;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

public class SwaggerConfig {

	@Bean
	OpenAPI customAPI() {
		return new OpenAPI().info(new Info().title("Projeto WellDone")
				.description("This a project for the Global Solution" + " Wink").version("1.1"));
	}

}

