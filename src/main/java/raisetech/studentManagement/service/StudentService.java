package raisetech.studentManagement.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.repository.StudentRepository;


@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    //何かしらの処理を行うために、サービスを作成する。
    this.repository = repository;
    this.converter = converter;
  }

  public List<StudentDetail> searchStudentList() {
    List<Student>studentList = repository.search();
    List<StudentCourse> studentsCoursesList = repository.courseSearch();
    return converter.convertStudentDetails(studentList, studentsCoursesList);
  }

  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();

    repository.registerStudent(student);
    List<StudentCourse>studentsCourses = studentDetail.getStudentCourseList();
    studentsCourses.forEach(studentCourse -> {
      initStudentsCourse(studentCourse, student);
      repository.registerStudentCourse(studentCourse);
    });
  }

  /**
   * 受講生コース情報を登録する際の初期情報を設定する。
   *
   * @param studentCourse
   * @param student
   */
  private void initStudentsCourse(StudentCourse studentCourse, Student student) {
    studentCourse.setStudentId(student.getId());
    studentCourse.setStartDate(new Date());
  }

  public StudentDetail findDetailById(long id) {
    Student student = repository.findById(id);
    List<StudentCourse>studentCourseList = repository.findByStudentId(id);
    return new StudentDetail(student, studentCourseList);
  }

  public void updateStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    repository.updateStudent(student);
    List<StudentCourse>studentCourse = studentDetail.getStudentCourseList();
    for(StudentCourse studentCourses : studentCourse) {
      repository.updateStudentCourses(studentCourses);
    }
  }

  public void delete(Long id) {
    Student student = repository.findById(id);
    student.setIsdeleted(true);
    repository.updateIsDeleted(student.getId(), true);
  }

}
