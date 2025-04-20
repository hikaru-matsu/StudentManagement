package raisetech.studentManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.service.StudentService;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public String getStudentList(Model model) {
    List<Student> students = service.searchStudentList();
    List<StudentsCourses> studentsCourses = service.searchStudentCourseList();
    model.addAttribute("studentList", converter.convertStudentDetails(students, studentsCourses));

    return "studentList";
  }

  @GetMapping("/studentCourseList")
  public String getStudentCourseList(Model model) {
    model.addAttribute("studentCourseList", service.searchStudentCourseList());
    return "studentCourseList";
  }

  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    StudentDetail studentDetail = new StudentDetail();
    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }

  @GetMapping("/updateStudent/{id}")
  public String findById(@PathVariable("id") long id, Model model) {
    StudentDetail studentDetail = service.findDetailById(id);
    model.addAttribute("studentDetail", studentDetail);
    return "updateStudent";
  }

  @PostMapping("/updateStudent/{id}")
  public String updateStudent(@ModelAttribute StudentDetail studentDetail) {
    service.editStudent(studentDetail);
    return "redirect:/studentList";
  }


  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute("studentDetail") StudentDetail studentDetail) {
    service.saveStudent(studentDetail);
    return "redirect:/studentList";
  }

  @GetMapping("/deleteStudent/{id}")
    public String delete(@PathVariable Long id, Model model) {
      service.delete(id);
      return "redirect:/studentList";
  }

}
