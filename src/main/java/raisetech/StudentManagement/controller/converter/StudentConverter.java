package raisetech.StudentManagement.controller.converter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;

@Component
public class StudentConverter {
  public List<StudentDetail> convertStudentDetail(List<Student> studentList, List<StudentCourse> studentCourseList) {
    List<StudentDetail> studentDetailList = new ArrayList<>();
    studentList.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      List<StudentCourse> convertStudentCourse = new ArrayList<>();
      studentCourseList.stream()
          .filter(studentCourses -> student.getId() == studentCourses.getStudentId())
          .forEach(studentCourses -> {
            studentDetail.setStudent(student);
            convertStudentCourse.add(studentCourses);
          });
      studentDetail.setStudentCourses(convertStudentCourse);
      studentDetailList.add(studentDetail);
    });
    return studentDetailList;
  }
}
