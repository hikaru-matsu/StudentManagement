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
  private StudentConverter studentConveter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter studentConverter) {
    this.repository = repository;
    this.studentConveter = studentConverter;
  }

  public List<StudentDetail> searchStudentDetail() {
    List<Student>studentList = repository.search();
    List<StudentCourse>studentCourseList = repository.searchStudentCourse();
    return studentConveter.convertStudentDetail(studentList, studentCourseList);
  }

  public StudentDetail studentDetailFindById(Integer id) {
    Student student = repository.studentFindById(id);
    List<StudentCourse> studentCourse = repository.studentCoursesFindById(student.getId());
    return new StudentDetail(student, studentCourse);
  }

@Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());
    for(StudentCourse studentCourse : studentDetail.getStudentCourse()) {
      studentCourse.setStudentId(studentDetail.getStudent().getId());
      studentCourse.setStartDate(LocalDate.now());
      repository.registerStudentCourse(studentCourse);
    }
    return studentDetail;
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