package raisetech.studentManagement.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.repository.StudentRepository;


@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    //何かしらの処理を行うために、サービスを作成する。
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.search();
  }

  public List<StudentsCourses> searchStudentCourseList() {
    return repository.courseSearch();
  }

  @Transactional
  public void saveStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    repository.insertStudent(student);

    int studentId = student.getId();
    List<StudentsCourses> courses = studentDetail.getStudentsCourses();
    for(StudentsCourses course : courses) {
      course.setStudentId(studentId);
      course.setStartDate(LocalDate.now());
      repository.insertStudentCourse(course);
    }
  }


}