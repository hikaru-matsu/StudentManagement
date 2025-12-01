package raisetech.StudentManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "受講生管理システム", description = "受講生の情報や受講しているコースの情報を管理するシステムです"))
@SpringBootApplication
public class StudentManagementApplication {
	public static void main(String[] args) {
		//localhost:8080
		SpringApplication.run(StudentManagementApplication.class, args);
	}
}
