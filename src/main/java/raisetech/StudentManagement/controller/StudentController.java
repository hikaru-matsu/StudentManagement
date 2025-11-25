package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@RestController
public class StudentController {
  private StudentService service;
  private StudentConverter studentConveter;

  @Autowired
  public StudentController(StudentService service, StudentConverter studentConverter) {
    this.service = service;
    this.studentConveter = studentConverter;
  }

  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    List<Student>studentList = service.searchStudentList();
    List<StudentCourse>studentCourseList = service.searchStudentCourseList();
    return studentConveter.convertStudentDetail(studentList, studentCourseList);
   }

   @PostMapping("/registerStudent")
  public ResponseEntity<String> registerStudent(@RequestBody StudentDetail studentDetail) {
    service.registerStudent(studentDetail);
    return ResponseEntity.ok("登録処理が成功しました！");
   }

   @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました！");
   }
}
