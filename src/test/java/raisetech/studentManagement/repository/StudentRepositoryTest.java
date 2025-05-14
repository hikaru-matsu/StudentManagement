package raisetech.studentManagement.repository;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.fixture.TestData;

@MybatisTest
@AutoConfigureTestDatabase(replace = NONE)
class StudentRepositoryTest {

  Student student;
  StudentCourse studentCourse;
  StudentDetail studentDetail;
  List<StudentCourse> sqlStudentCourse = new ArrayList<>();
  List<Student> sqlStudent = new ArrayList<>();

  @Autowired
  private StudentRepository sut;

  @BeforeEach
  void before() {
    student = TestData.testStudent();
    studentCourse = TestData.testStudentCourse();
    studentDetail = TestData.testStudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(List.of(studentCourse));

    sqlStudent.add(
        new Student(1, "佐藤かずま", "サトウカズマ", "かずまさん", "Kazuma@example.com", "日本", 19,
            "男性", "", false));
    sqlStudent.add(
        new Student(2, "アクア", "アクア", "駄目神", "Akua@example.com", "天界", 1000, "女性", "",
            false));
    sqlStudent.add(
        new Student(3, "めぐみん", "メグミン", "頭のおかしな子", "Megumin@example.com", "紅魔郷",
            18, "女性", "", false));
    sqlStudent.add(
        new Student(4, "ダクネス", "ダクネス", "ララティーナ", "Dakunes@exapmle.com", "アクセル",
            20, "女性", "", false));

    sqlStudentCourse.add(new StudentCourse(1, 1, "冒険者", LocalDate.of(2016, 5, 10), null));
    sqlStudentCourse.add(new StudentCourse(2, 1, "引きニート", LocalDate.of(2014, 12, 24), null));
    sqlStudentCourse.add(
        new StudentCourse(3, 2, "アークプリースト", LocalDate.of(2016, 5, 10), null));
    sqlStudentCourse.add(
        new StudentCourse(4, 3, "アークウィザード", LocalDate.of(2016, 3, 31), null));
    sqlStudentCourse.add(new StudentCourse(5, 4, "クルセイダー", LocalDate.of(2015, 4, 1), null));
  }

  @Test
  void 受講生の全件検索が実行できること() {
    List<Student> actual = sut.search();
    sqlStudent.equals(actual);
  }

  @Test
  void 受講生コース情報の全件検索が実行できること() {
    List<StudentCourse> actual = sut.courseSearch();
    sqlStudentCourse.equals(actual);
  }

  @Test
  void 受講生の登録が実行できること() {
    sut.registerStudent(student);
    List<Student> actual = sut.search();
    sqlStudent.add(
        new Student(5, "テスト太郎", "テストタロウ", "テスト君", "Test@example.com",
            "どこか", 100,
            "不明", "これはテスト専用データです。", false));
    sqlStudent.equals(actual);
  }

  @Test
  void 受講生コース情報の登録が実行できること() {
    sut.registerStudentCourse(studentCourse);
    List<StudentCourse> actual = sut.courseSearch();
    sqlStudentCourse.add(new StudentCourse(99, 99, "テストコース", LocalDate.of(5555, 5, 5),
        LocalDate.of(9999, 9, 9)));
    sqlStudent.equals(actual);
  }

  @Test
  void 受講生詳細の削除処理が実行できること() {
    sut.updateIsDeleted(4, true);
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    studentList.add(
        new Student(1, "佐藤かずま", "サトウカズマ", "かずまさん", "Kazuma@example.com", "日本", 19,
            "男性", "", false));
    studentList.add(
        new Student(2, "アクア", "アクア", "駄目神", "Akua@example.com", "天界", 1000, "女性", "",
            false));
    studentList.add(
        new Student(3, "めぐみん", "メグミン", "頭のおかしな子", "Megumin@example.com", "紅魔郷",
            18, "女性", "", false));
    studentCourseList.add(new StudentCourse(1, 1, "冒険者", LocalDate.of(2016, 5, 10), null));
    studentCourseList.add(new StudentCourse(2, 1, "引きニート", LocalDate.of(2014, 12, 24), null));
    studentCourseList.add(
        new StudentCourse(3, 2, "アークプリースト", LocalDate.of(2016, 5, 10), null));
    studentCourseList.add(
        new StudentCourse(4, 3, "アークウィザード", LocalDate.of(2016, 3, 31), null));
    List<Student> actualStudent = sut.search();
    List<StudentCourse> actualStudentCourse = sut.courseSearch();
    studentList.equals(actualStudent);
    studentCourseList.equals(actualStudentCourse);
  }

  @Test
  void 受講生のIdが取得できて更新処理が実行できていること() {
    sut.registerStudent(student);
    Student newStudent = sut.findById(5);
    newStudent.setName("アップデートネーム");
    sut.updateStudent(newStudent);
    List<Student> actual = sut.search();
    sqlStudent.add(
        new Student(5, "アップデートネーム", "テストタロウ", "テスト君", "Test@example.com",
            "どこか", 100,
            "不明", "これはテスト専用データです。", false));
    sqlStudent.equals(actual);
  }

  @Test
  void 受講生のIdが取得できてコース情報の更新処理が実行できていること() {
    sut.registerStudentCourse(studentCourse);
    List<StudentCourse> StudentCourseList = sut.courseSearch();
    StudentCourse newStudentCourse = StudentCourseList.get(5);
    newStudentCourse.setCourseName("アップデートコース");
    sut.updateStudentCourses(newStudentCourse);
    sqlStudentCourse.add(new StudentCourse(99, 99, "アップデートコース", LocalDate.of(5555, 5, 5),
        LocalDate.of(9999, 9, 9)));
    List<StudentCourse> actual = sut.courseSearch();
    sqlStudentCourse.equals(actual);
  }
}