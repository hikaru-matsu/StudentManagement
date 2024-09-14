package raisetech.StudentManagement.service;

import java.util.List;
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
    //検索処理
    return repository.search();
    //絞り込み検索、年齢が30代の人のみを抽出する。
    //抽出したリストをコントローラーに返す。
  }

  public List<StudentsCourses> searchStudentCourseList() {
    //絞り込み処理、抽出処理。特定のコース情報を抽出する。
    return repository.searchStudentsCourses();
  }
}
