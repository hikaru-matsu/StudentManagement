package raisetech.StudentManagement.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    StudentDetail studentDetail = new StudentDetail();
    StudentCourse studentCourse = new StudentCourse();
    List<StudentCourse>studentCourseList = Arrays.asList(studentCourse);
    studentDetail.setStudentCourse(studentCourseList);
    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
   }

   @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if(result.hasErrors()) {
      return "registerStudent";
    }
    service.registerStudent(studentDetail);
    return "redirect:/studentList";
   }

   @GetMapping("/studentList/{id}")
  public String getStudent(@PathVariable("id") Integer id, Model model) {
    StudentDetail studentDetail = service.studentDetailFindById(id);
    model.addAttribute(studentDetail);
    return "updateStudent";
   }

   @PostMapping("/updateStudent")
  public String updateStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if(result.hasErrors()) {
      return "updateStudent";
    }
    service.updateStudent(studentDetail);
    service.deleteStudent(studentDetail);
    return "redirect:/studentList";
   }

}
