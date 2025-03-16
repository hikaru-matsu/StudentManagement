package raisetech.studentManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

@Autowired
private StudentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);

	}

	@GetMapping("/studentInfo")
	public String getStudentInfo(@RequestParam int id) {
		Student student = repository.searchById(id);
		//return student.getName() + " " + student
		// .getAge();
		List<String>studentInfo = Arrays.asList(
			student.getName(),
			student.getFurigana(),
			student.getNickname(),
			student.getEmail(),
			student.getRegion(),
			student.getGender()
		);
		return String.join(", ", studentInfo);
	}
}

