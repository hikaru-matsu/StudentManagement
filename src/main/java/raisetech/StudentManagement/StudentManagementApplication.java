package raisetech.StudentManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	private String name = "Matsubara Hikaru";
	private Integer age = 26;
	private Map<String, Integer> studentMap = new HashMap<>();

	public static void main(String[] args) {
		//localhost:8080
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@GetMapping("/studentInfo")
	public String getName() {
		return name + " : " + age + "歳";
	}

	@PostMapping("/studentInfo")
	public void setName(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@GetMapping("/studentMap")
	public List<String> getStudentMap() {
		List<String> studentList = new ArrayList<>();
		for(Map.Entry<String, Integer>studentMap :studentMap.entrySet()) {
				String key = studentMap.getKey();
				int value = studentMap.getValue();
				studentList.add(key + " : " + value);
		}
		return studentList;
	}

	@PostMapping("/studentMap")
	public void setStudentMap(String name, Integer age) {
		studentMap.put(name, age);
	}

	@PostMapping("/updateStudentMap")
	public void updateStudentMap(String name, int age) {
		for(String key : studentMap.keySet()) {
			if(key.equals(name)) {
				studentMap.replace(key, age);
				}
			}
		}
	}
