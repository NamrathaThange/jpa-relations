package com.pradeep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title="Project,Project User API'S", version = "1.0", description = "Provides realted to project and project user services"))
@SpringBootApplication
public class ProjectDirectoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectDirectoryApplication.class, args);
	}

}
