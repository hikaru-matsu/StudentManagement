package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {
  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.search();
  }

  public List<StudentCourse> searchStudentCourseList() {
    return repository.searchStudentCourse();
  }

  public StudentDetail studentDetailFindById(Integer id) {
    StudentDetail studentDetail = new StudentDetail();
    Student student = repository.studentFindById(id);
    studentDetail.setStudent(student);
    Integer studentId = id;
    List<StudentCourse> studentCourseList = repository.studentCourseFindById(studentId);
    studentDetail.setStudentCourse(studentCourseList);
    return studentDetail;
  }

@Transactional
  public void registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    repository.registerStudent(student);

    StudentCourse studentCourse = new StudentCourse();
    Integer id = student.getId();
    studentCourse.setStudentId(id);

    for(StudentCourse course : studentDetail.getStudentCourse()) {
      if(studentDetail.getStudent().getId() == id) {
        studentCourse.setCourseName(course.getCourseName());
      }
    }
    repository.registerStudentCourse(studentCourse);
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    repository.updateStudent(student);
    List<StudentCourse>studentCourseList = studentDetail.getStudentCourse();

    for(StudentCourse studentCourse : studentCourseList) {
        repository.updateStudentCourse(studentCourse);
    }
  }
}