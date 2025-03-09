package raisetech.studentManagement;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	private String name = "Hikaru";
	private int age = 25;

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}
@GetMapping("/studentInfo")
	public String getStudentInfo() {
		return name + " " + age;
	}

	@PostMapping("/studentInfo")
		public void setStudentInfo(String name, int age){
		this.name = name;
		this.age = age;
	}
}
