package raisetech.studentManagement.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
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
    List<StudentsCourses> studentsCoursesList = repository.courseSearch();
    return converter.convertStudentDetails(studentList, studentsCoursesList);
  }

  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    repository.registerStudent(student);

    List<StudentsCourses>studentsCourses = studentDetail.getStudentsCourses();
    for (StudentsCourses studentCourse : studentsCourses) {
      studentCourse.setStudentId(student.getId());
      studentCourse.setStartDate(new Date());
      repository.registerStudentsCourses(studentCourse);
    }
  }

  public StudentDetail findDetailById(long id) {
    Student student = repository.findById(id);
    List<StudentsCourses>studentCourses = repository.findByStudentId(id);
    return new StudentDetail(student, studentCourses);
  }

  public void updateStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();
    repository.updateStudent(student);
  }

  public void delete(Long id) {
    Student student = repository.findById(id);
    student.setIsdeleted(true);
    repository.updateIsDeleted(student.getId(), true);
  }

}
