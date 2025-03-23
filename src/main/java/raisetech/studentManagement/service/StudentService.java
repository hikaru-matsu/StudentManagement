package raisetech.studentManagement.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
import raisetech.studentManagement.repository.StudentRepository;


@Service

public class StudentService {
  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    //検索処理
    List<Student> students = repository.search();
    List<Student>filteredStudentList = new ArrayList<>();
    //絞り込み検索、「年齢が30代のだけ抽出する」
    //抽出したリストをコントローラーに返す。
    for(Student student : students) {
      int age = student.getAge();
        if(age > 30 && age < 40) {
          filteredStudentList.add(student);
        }
    }
    return filteredStudentList;
  }

  public List<StudentsCourses> searchStudentCourse() {

    List<StudentsCourses> studentCourseList = repository.courseSearch();
    List<StudentsCourses> filteredStudentCourse = new ArrayList<>();

    //「javaコース」のみを抽出して検索する。

    for(StudentsCourses studentCourse : studentCourseList) {
      String course = studentCourse.getCourseName();
      if(course.equals("Javaコース")) {
        filteredStudentCourse.add(studentCourse);
      }
    }
    return filteredStudentCourse;
  }

}
