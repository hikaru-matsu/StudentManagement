package raisetech.studentManagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;
  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細の一覧検索機能＿リポジトリとコンバーターの処理が適切に呼び出せていること() {
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    when(repository.search()).thenReturn(studentList);
    when(repository.courseSearch()).thenReturn(studentCourseList);

    sut.searchStudentList();

    verify(repository, times(1)).search();
    verify(repository, times(1)).courseSearch();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
  }

  @Test
  void 受講生詳細の単一検索機能＿リポジトリの処理が適切に呼び出せていること() {
    Student student = new Student();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    student.setId(1);

    when(repository.findById(1)).thenReturn(student);
    when(repository.findByStudentId(1)).thenReturn(studentCourseList);
    sut.findDetailById(1);

    verify(repository, times(1)).findById(1);
    verify(repository, times(1)).findByStudentId(1);
  }

  @Test
  void 受講生詳細の新規登録機能＿リポジトリの処理が適切に呼び出せていること() {
    StudentDetail studentDetail = new StudentDetail();
    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    studentCourseList.add(studentCourse);

    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(studentCourseList);

    sut.registerStudent(studentDetail);

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(1)).registerStudentCourse(studentCourse);
  }

  @Test
  void 受講生詳細の更新処理機能＿リポジトリの処理が適切に呼び出せていること() {
    StudentDetail studentDetail = new StudentDetail();
    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    studentCourseList.add(studentCourse);
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(studentCourseList);

    sut.updateStudent(studentDetail);

    verify(repository, times(1)).updateStudent(student);
    verify(repository, times(1)).updateStudentCourses(studentCourse);
  }

  @Test
  void 受講生詳細の論理削除機能＿リポジトリの処理が適切に呼び出せていること(){
    Student student = new Student();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    student.setId(1);
    when(repository.findById(1L)).thenReturn(student);

    sut.delete(1L);

    verify(repository, times(1)).findById(1L);
    verify(repository, times(1)).updateIsDeleted(1L, true);
  }
}
