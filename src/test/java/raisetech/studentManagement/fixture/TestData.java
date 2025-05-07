package raisetech.studentManagement.fixture;

import java.time.LocalDate;
import java.util.List;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;

public class TestData {
  private TestData() {}

  public static Student testStudent() {
    return Student.builder()
        .id(99)
        .name("テスト太郎")
        .kanaName("テストタロウ")
        .nickname("テストくん")
        .email("Test@example.com")
        .region("どこか")
        .age(100)
        .gender("不明")
        .remark("これはテスト専用データです。")
        .build();
  }

  public static StudentCourse testStudentCourse() {
    return StudentCourse.builder()
        .id(99)
        .studentId(99)
        .courseName("テストコース")
        .startDate(LocalDate.of(5555, 5, 5))
        .endDate(LocalDate.of(7777, 7, 7))
        .build();
  }

  public static StudentDetail testStudentDetail() {
    return StudentDetail.builder()
        .student(testStudent())
        .studentCourseList(List.of(testStudentCourse()))
        .build();
  }

}
