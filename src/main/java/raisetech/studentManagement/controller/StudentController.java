package raisetech.studentManagement.controller;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.service.StudentService;

@RestController
public class StudentController  {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {

    List<Student> students = service.searchStudentList();
    List<StudentsCourses> studentsCourses = service.searchStudentCourse();

    List<StudentDetail> studentDetails = new ArrayList<>();

    for(Student student : students) {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentsCourses>convertStudentCourse = new ArrayList<>();

      for (StudentsCourses studentCourse : studentsCourses) {
        if(student.getId() == studentCourse.getStudentId()) {
          convertStudentCourse.add(studentCourse);
        }
      }
      studentDetail.setStudentsCourses(convertStudentCourse);
      studentDetails.add(studentDetail);
    }
    return studentDetails;
   }

  @GetMapping("/studentCourseList")
  public List<StudentsCourses> getStudentCourseList() {
    return service.searchStudentCourse();
  }
}
