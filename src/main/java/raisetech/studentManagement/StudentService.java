package raisetech.studentManagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentService {

  @Autowired
  private StudentRepository repository;

  public List<Student> getAllStudents() {
    return repository.findAllStudents();
  }

}
