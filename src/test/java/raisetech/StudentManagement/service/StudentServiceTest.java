package raisetech.StudentManagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.bytebuddy.build.ToStringPlugin.Enhance;
import org.apache.ibatis.jdbc.Null;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  private StudentService sut;
  private Student student;
  private StudentCourse studentCourse;
  private List<StudentCourse>studentCourseList;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
    student = new Student();
    studentCourse = new StudentCourse();
    studentCourseList = new ArrayList<>();
  }

  @Test
  void 受講生一覧検索機能＿リポジトリとコンバーターの処理が適切に呼び出せていること() {
    List<Student>studentList = new ArrayList<>();
    List<StudentDetail>expected = new ArrayList<>();
    List<StudentDetail> studentDetailList = new ArrayList<>();

    when(repository.searchStudent()).thenReturn(studentList);
    when(repository.searchStudentCourse()).thenReturn(studentCourseList);
    when(converter.convertStudentDetail(studentList, studentCourseList)).thenReturn(studentDetailList);

    List<StudentDetail>actual = sut.searchStudentDetail();

    Mockito.verify(repository, times(1)).searchStudent();
    Mockito.verify(repository, times(1)).searchStudentCourse();
    Mockito.verify(converter, times(1)).convertStudentDetail(studentList, studentCourseList);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  void 受講生単一検索機能＿リポジトリの処理が適切に呼び出せていること() {
    Integer id = 99;
    student.setId(id);
    studentCourse.setStudentId(id);
    studentCourseList.add(studentCourse);
    StudentDetail expected = new StudentDetail();
    expected.setStudent(student);
    expected.setStudentCourse(studentCourseList);

    when(repository.studentFindById(id)).thenReturn(student);
    when(repository.studentCoursesFindById(id)).thenReturn(studentCourseList);
    StudentDetail actual = sut.studentDetailFindById(id);

    Mockito.verify(repository, times(1)).studentFindById(id);
    Mockito.verify(repository, times(1)).studentCoursesFindById(id);

    Assertions.assertEquals(expected,  actual);
  }

  @Test
  void 受講生単一検索機能＿引数がnullの場合() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      sut.studentDetailFindById(null);
    });
  }

  @Test
  void 受講生単一検索機能＿引数が存在しないIDの場合() {
    Exception exception = assertThrows(NullPointerException.class, () -> {
      sut.studentDetailFindById(0);
    });
  }

  @Test
  void 受講生単一検索機能＿リポジトリで例外がスローされた場合(){
    when(repository.studentFindById(null)).thenThrow(new RuntimeException("リポジトリから例外がスローされる！"));

    assertThrows(RuntimeException.class, () -> {
      sut.studentDetailFindById(null);
    });
  }



  @Test
  void 受講生登録機能＿リポジトリの処理が適切に呼びされていること() {
    studentCourseList.add(studentCourse);
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourse(studentCourseList);

    sut.registerStudent(studentDetail);

    Mockito.verify(repository, times(1)).registerStudent(student);
    Mockito.verify(repository, times(1)).registerStudentCourse(studentCourse);
  }

  @Test
  void 受講生更新処理機能＿リポジトリの処理が適切に呼び出されていること() {
    studentCourseList.add(studentCourse);
    StudentDetail studentDetail = new StudentDetail(student, studentCourseList);
    sut.updateStudent(studentDetail);

    Mockito.verify(repository, times(1)).updateStudent(student);
    Mockito.verify(repository, times(1)).updateStudentCourse(studentCourse);
  }

}
