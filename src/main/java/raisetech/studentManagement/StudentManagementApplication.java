package raisetech.studentManagement;

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
		public String getStudent(@RequestParam String name) {
			Student student = repository.searchByName(name);
			return student.getName() + " " + student.getAge() + "歳";
		}

	@GetMapping("/students")
	public Map<String, Integer> getAllStudents() {
		List<Student> students = repository.getAllStudents();
		Map<String, Integer> studentMap = new HashMap<>();

		for (Student student : students) {
			studentMap.put(student.getName(), student.getAge());
		}
		return studentMap;

	}


	@PostMapping("/studentInfo")
		void registerStudent(String name, int age) {
		repository.registerStudent(name, age);
	}
}
