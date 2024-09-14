package raisetech.StudentManagement.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    // 検索処理
    List<Student> students = repository.search();

    // 35歳以上の学生のみを抽出する
    List<Student> studentsAbove35 = students.stream()
        .filter(student -> student.getAge() >= 35)
        .collect(Collectors.toList());

    // 35歳以上の学生がいる場合、そのリストを返す
    if (!studentsAbove35.isEmpty()) {
      return studentsAbove35;  // 35歳以上の学生がいる場合、そのリストを返す
    }

    // いない場合、年齢が30代の人のみを抽出する
    List<Student> filteredStudents = students.stream()
        .filter(student -> student.getAge() >= 30 && student.getAge() < 40)
        .collect(Collectors.toList());

    // 絞り込んだリストを返す
    return filteredStudents;
  }



  public List<StudentsCourses> searchStudentCourseList() {
    //絞り込み処理、抽出処理。特定のコース情報を抽出する。
    return repository.searchStudentsCourses();
  }
}
