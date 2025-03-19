package raisetech.studentManagement.service;


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
    repository.search();
    //絞り込み検索、「年齢が30代のだけ抽出する」
    //抽出したリストをコントローラーに返す。
  }

  public List<StudentsCourses> searchStudentCourse() {

    repository.courseSearch();

    //「javaコース」のみを抽出して検索する。
    return repository.courseSearch();
  }

}
