package raisetech.studentManagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService service;

	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return service.getAllStudents();
	}
}
