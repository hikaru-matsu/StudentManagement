package raisetech.studentManagement.fixture;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;

public class TestData {

  public static Student testStudent() {
    return new Student(
        99,
        "テスト太郎",
        "テストタロウ",
        "テスト君",
        "Test@example.com",
        "どこか",
        100,
        "不明",
        "これはテスト専用データです。"
    );
  }

  public static StudentCourse testStudentCourse() {
    return new StudentCourse(
        99,
        99,
        "テストコース",
        LocalDate.of(5555, 5, 5),
        LocalDate.of(9999, 9, 9)
    );
  }

  public static StudentDetail testStudentDetail() {
    return new StudentDetail(
        testStudent(),
        List.of(testStudentCourse())
    );
  }
}