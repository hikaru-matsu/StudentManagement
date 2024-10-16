package raisetech.StudentManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.repository.StudentRepository;

@Service

/**
 * 受講生情報の中核を担う処理を行うコードを書く
 * MVCモデルで考えると、Modelに位置する。
 */

public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.search();
  }

  public List<StudentCourse> searchStudentCourseList() {
    //絞り込み検索で、特定のコース情報のみを抽出して、コントローラーに返す。
    return repository.searchStudentCourseList();
  }
}
