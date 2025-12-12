package raisetech.StudentManagement.repository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件が取得できること() {
    List<Student> actual = sut.searchStudent();
    assertThat(actual.size()).isEqualTo(3);
  }

  @Test
  void 受講生コース情報が全件取得できること() {
    List<StudentCourse>actual = sut.searchStudentCourse();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 一件の受講生情報が取得できること() {
    Student actual = sut.studentFindById(1);
    List<Student>actualList = List.of(actual);
    List<Student>studentList = sut.searchStudent();

    assertThat(studentList.contains(actual)).isEqualTo(true);
    assertThat(actualList.size()).isEqualTo(1);
  }

  @Test
  void 一件の受講生コース情報が取得できること() {
    List<StudentCourse> actual = sut.studentCoursesFindById(1);
    List<StudentCourse>studentCourseList = sut.searchStudentCourse();

    for(StudentCourse studentCourse : actual) {
      assertThat(studentCourseList.contains(studentCourse)).isEqualTo(true);
    }
    assertThat(actual.size()).isEqualTo(1);
  }

  @Test
  void 存在しないIDでは受講生情報が適切に取得できないこと() {
    Student actual = sut.studentFindById(0);
    assertThat(actual).isEqualTo(null);
  }

  @Test
  void 存在しない受講生IDでは受講生コース情報が適切に取得できないこと() {
    List<StudentCourse>actual = sut.studentCoursesFindById(0);

    assertThat(actual.size()).isEqualTo(0);
  }


  @Test
  void 受講生の登録が行えること() {
    Student student = new Student();
    student.setName("松原光");
    student.setKanaName("マツバラヒカル");
    student.setNickname("ヒカル");
    student.setEmail("Hikaru@example.com");
    student.setArea("奈良県");
    student.setAge(26);
    student.setGender("男性");

    sut.registerStudent(student);
    List<Student>actual = sut.searchStudent();
    assertThat(actual.size()).isEqualTo(4);
  }

  @Test
  void 受講生コース情報の登録が行えること() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId(1);
    studentCourse.setCourseName("適当コース");
    studentCourse.setStartDate(LocalDate.now());

    sut.registerStudentCourse(studentCourse);
    List<StudentCourse> actual = sut.searchStudentCourse();
    assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void 受講生情報の更新が行えること() {
    Student student = new Student();

    student.setId(1);
    student.setName("テスト花子");
    student.setKanaName("テストハナコ");
    student.setNickname("花子");
    student.setEmail("Test@example.com");
    student.setArea("異世界");
    student.setAge(0);
    student.setGender("女性");
    student.setRemark("");
    student.setIsDeleted(false);

    sut.updateStudent(student);

    Student actual = sut.studentFindById(1);

    assertThat(actual).isEqualTo(student);
  }

  @Test
  void 受講生コース情報の更新が行えること() {
    StudentCourse studentCourse = new StudentCourse();

    studentCourse.setId(1);
    studentCourse.setStudentId(1);
    studentCourse.setCourseName("適当コース");

    sut.updateStudentCourse(studentCourse);

    List<StudentCourse> actual = sut.studentCoursesFindById(1);
    List<StudentCourse>studentCourseList = List.of(studentCourse);

    assertThat(actual).isEqualTo(studentCourseList);
    assertThat(actual.size()).isEqualTo(1);
  }
}