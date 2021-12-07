package com.employee.sample.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employee CRUD Operations", version = "3.0", description = "Employees Information"))
public class SampleProjectAssignmentSolutionArchitectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleProjectAssignmentSolutionArchitectApplication.class, args);
	}

}
