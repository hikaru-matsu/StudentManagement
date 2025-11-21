package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
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

   @GetMapping("/newStudent")
   public String newStudent(Model model) {
    model.addAttribute("studentDetail", new StudentDetail());
    model.addAttribute("studentCourse", new StudentCourse());
    return "registerStudent";
   }

   @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, StudentCourse studentCourse, BindingResult result) {
    if(result.hasErrors()) {
      return "studentList";
    }
    service.registerStudent(studentDetail, studentCourse);
    return "redirect:/studentList";
   }
}
