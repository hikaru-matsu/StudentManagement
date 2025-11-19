package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.service.StudentService;

@Controller
public class StudentController {
  private StudentService service;
  private StudentConverter studentConveter;

  @Autowired
  public StudentController(StudentService service, StudentConverter studentConverter) {
    this.service = service;
    this.studentConveter = studentConverter;
  }

  @GetMapping("/studentList")
  public String getStudentList(Model model) {
    List<Student>studentList = service.searchStudentList();
    List<StudentCourse>studentCourseList = service.searchStudentCourseList();
    model.addAttribute("studentList", studentConveter.convertStudentDetail(studentList, studentCourseList));
    return "studentList";
   }

  @GetMapping("/studentCourseList")
  public List<StudentCourse> getStudentCourseList() {
    return service.searchStudentCourseList();
  }

}
