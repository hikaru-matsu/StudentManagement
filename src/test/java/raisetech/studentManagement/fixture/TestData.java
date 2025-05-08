package raisetech.studentManagement.fixture;

import java.time.LocalDate;
import java.util.List;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;

public class TestData {

  public static Student testStudent() {
    Student student = new Student();
    student.setId(99);
    student.setName("テスト太郎");
    student.setKanaName("テストタロウ");
    student.setNickname("テスト君");
    student.setEmail("Test@example.com");
    student.setRegion("どこか");
    student.setAge(100);
    student.setGender("不明");
    student.setRemark("これはテスト専用のデータです。");
    return student;
  }

  public static StudentCourse testStudentCourse() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId(99);
    studentCourse.setStudentId(99);
    studentCourse.setCourseName("テストコース");
    studentCourse.setStartDate(LocalDate.of(5555, 5, 5));
    studentCourse.setEndDate(LocalDate.of(9999,9,9));
    return studentCourse;
  }

  public static StudentDetail testStudentDetail() {
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(testStudent());
    studentDetail.setStudentCourseList(List.of(testStudentCourse()));
    return studentDetail;
  }

}