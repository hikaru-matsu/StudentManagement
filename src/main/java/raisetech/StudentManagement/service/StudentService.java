package raisetech.StudentManagement.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

/**
 * 受講生情報を取り扱うService
 */
@Service
public class StudentService {
  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  public List<StudentDetail> searchStudentDetail() {
    List<Student>studentList = repository.searchStudent();
    List<StudentCourse>studentCourseList = repository.searchStudentCourse();
    return converter.convertStudentDetail(studentList, studentCourseList);
  }

  public StudentDetail studentDetailFindById(Integer id) {
    Student student = repository.studentFindById(id);
    List<StudentCourse> studentCourse = repository.studentCoursesFindById(student.getId());
    return new StudentDetail(student, studentCourse);
  }

@Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    repository.registerStudent(student);
    studentDetail.getStudentCourse().forEach(studentCourse -> {
      initStudentsCourse(studentCourse, student);
      repository.registerStudentCourse(studentCourse);
    });
    return studentDetail;
  }

  private void initStudentsCourse(StudentCourse studentCourse, Student student) {
    studentCourse.setStudentId(student.getId());
    studentCourse.setStartDate(LocalDate.now());
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    for(StudentCourse studentCourse : studentDetail.getStudentCourse()) {
        repository.updateStudentCourse(studentCourse);
    }
  }

}