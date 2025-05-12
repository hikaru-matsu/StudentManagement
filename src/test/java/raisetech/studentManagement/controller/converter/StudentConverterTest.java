package raisetech.studentManagement.controller.converter;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.fixture.TestData;

class StudentConverterTest {

  Student student;
  StudentCourse studentCourse;
  StudentDetail studentDetail;
  private StudentConverter sut;

  @BeforeEach
  void before() {
    sut = new StudentConverter();
    student = TestData.testStudent();
    studentCourse = TestData.testStudentCourse();
    studentDetail = TestData.testStudentDetail();
  }

  @Test
  void 受講生情報と受講生コース情報を受け取って受講詳細に変換できていること() {
    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);
    StudentDetail expectDetail = new StudentDetail(student, List.of(studentCourse));
    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertEquals(List.of(expectDetail), actual);
  }
}