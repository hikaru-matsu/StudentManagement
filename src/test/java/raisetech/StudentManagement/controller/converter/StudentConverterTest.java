package raisetech.StudentManagement.controller.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;


class StudentConverterTest {

  private StudentConverter sut;
  private Student student;
  private StudentCourse studentCourse;
  private List<Student> studentList;
  private List<StudentCourse> studentCourseList;

  @BeforeEach
  void before() {
    sut = new StudentConverter();
    student = new Student();
    studentCourse = new StudentCourse();
    studentList = new ArrayList<>();
    studentCourseList = new ArrayList<>();
  }

  @Test
  void コンバータ処理が実行できて適切に変換ができていること() {
    student.setId(99);
    student.setName("テスト太郎");
    student.setKanaName("テストタロウ");
    student.setNickname("テッくん");
    student.setEmail("test@example.com");
    student.setArea("不思議の国");
    student.setAge(99);
    student.setGender("ガチ両刀");
    studentList.add(student);

    studentCourse.setId(99);
    studentCourse.setStudentId(99);
    studentCourse.setCourseName("テストコース");
    studentCourseList.add(studentCourse);

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourse(studentCourseList);

    List<StudentDetail>expect = sut.convertStudentDetail(studentList, studentCourseList);

    List<StudentDetail>actual = new ArrayList<>();
    actual.add(studentDetail);

    Assertions.assertEquals(expect, actual);
  }
}