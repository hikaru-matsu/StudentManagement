package raisetech.studentManagement.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.fixture.TestData;

@MybatisTest
class StudentRepositoryTest {

  Student student;
  StudentCourse studentCourse;
  StudentDetail studentDetail;

  @Autowired
  private StudentRepository sut;

  @BeforeEach
  void before() {
    student = TestData.testStudent();
    studentCourse = TestData.testStudentCourse();
    studentDetail = TestData.testStudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(List.of(studentCourse));
  }

  @Test
  void 受講生の全件検索が実行できること() {
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(4);
  }

  @Test
  void 受講生コース情報の全件検索が実行できること() {
    List<StudentCourse> actual = sut.courseSearch();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 受講生の登録が実行できること() {
    sut.registerStudent(student);
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 受講生コース情報の登録が実行できること() {
    sut.registerStudentCourse(studentCourse);
    List<StudentCourse> actual = sut.courseSearch();
    assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void 受講生詳細の削除処理が実行できること() {
    sut.updateIsDeleted(4, true);
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(3);
  }

  @Test
  void 受講生のIdが取得できて更新処理が実行できていること() {
    sut.registerStudent(student);
    Student newStudent = sut.findById(5);
    newStudent.setName("アップデートネーム");
    sut.updateStudent(newStudent);
    Student actual = sut.findById(5);
    String actualName = actual.getName();
    assertEquals("アップデートネーム", actualName);
  }

  @Test
  void 受講生のIdが取得できてコース情報の更新処理が実行できていること() {
    sut.registerStudentCourse(studentCourse);
    List<StudentCourse> testCourseList = sut.courseSearch();
    StudentCourse newCourse = testCourseList.get(5);
    newCourse.setCourseName("アップデートコース");
    sut.updateStudentCourses(newCourse);
    //List<StudentCourse> actual = sut.courseSearch();
    List<StudentCourse> actual = sut.findByStudentId(99);
    StudentCourse actualCourse = actual.get(0);
    String actualCourseName = actualCourse.getCourseName();
    assertEquals("アップデートコース", actualCourseName);
  }
}