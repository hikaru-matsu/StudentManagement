package raisetech.StudentManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {
  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    List<Student> extractionStudent = new ArrayList<>();
    for(Student student : repository.search()) {
      if(student.getAge() >= 30) {
        extractionStudent.add(student);
      }
    }
    return extractionStudent;
  }

  public List<StudentCourse> searchStudentCourseList() {
    List<StudentCourse> extractionStudentCourse = new ArrayList<>();
    for(StudentCourse studentCourse : repository.searchStudentCourse()) {
      if(studentCourse.getCourseName().equals("Javaコース")) {
        extractionStudentCourse.add(studentCourse);
      }
    }
    return extractionStudentCourse;
  }
}
