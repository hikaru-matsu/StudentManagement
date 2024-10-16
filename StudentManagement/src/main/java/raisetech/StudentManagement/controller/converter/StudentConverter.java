package raisetech.StudentManagement.controller.converter;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@Component
public class StudentConverter {
  private StudentService service;

  @Autowired
  public StudentConverter(StudentService service) {
    this.service = service;
  }

  public List<StudentDetail> convertStudentDetails() {
    List<Student> students = service.searchStudentList();
    List<StudentCourse> studentCourses = service.searchStudentCourseList();

    List<StudentDetail> studentDetail = new ArrayList<>();
    for (Student student : students) {
      StudentDetail studentDetails = new StudentDetail();
      studentDetails.setStudent(student);

      List<StudentCourse> convertStudentCourses = new ArrayList<>();
      for (StudentCourse studentCourse : studentCourses) {
        if (student.getId().equals(studentCourse.getStudentId())) {
          convertStudentCourses.add(studentCourse);
        }
      }
      studentDetails.setStudentCourse(convertStudentCourses);
      studentDetail.add(studentDetails);
    }
    return studentDetail;
  }
}
