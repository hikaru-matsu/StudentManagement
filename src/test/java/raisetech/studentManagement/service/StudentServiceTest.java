package raisetech.studentManagement.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.fixture.TestData;
import raisetech.studentManagement.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  Student student;
  StudentCourse studentCourse;
  StudentDetail studentDetail;

  @Mock
  private StudentRepository repository;
  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
    student = TestData.testStudent();
    studentCourse = TestData.testStudentCourse();
    studentDetail = TestData.testStudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(List.of(studentCourse));
  }

  @Test
  void 受講生詳細の一覧検索機能＿リポジトリとコンバーターの処理が適切に呼び出せていること() {
    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);
    when(repository.search()).thenReturn(studentList);
    when(repository.courseSearch()).thenReturn(studentCourseList);

    sut.searchStudentList();

    verify(repository, times(1)).search();
    verify(repository, times(1)).courseSearch();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
  }

  @Test
  void 受講生詳細の単一検索機能＿リポジトリの処理が適切に呼び出せていること() {
    List<StudentCourse> studentCourseList = List.of(studentCourse);
    student.setId(99);

    when(repository.findById(99)).thenReturn(student);
    when(repository.findByStudentId(99)).thenReturn(studentCourseList);
    sut.findDetailById(99);

    verify(repository, times(1)).findById(99);
    verify(repository, times(1)).findByStudentId(99);
  }

  @Test
  void 受講生詳細の新規登録機能＿リポジトリの処理が適切に呼び出せていること() {
    sut.registerStudent(studentDetail);

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(1)).registerStudentCourse(studentCourse);
  }

  @Test
  void 受講生詳細の更新処理機能＿リポジトリの処理が適切に呼び出せていること() {
    sut.updateStudent(studentDetail);

    verify(repository, times(1)).updateStudent(student);
    verify(repository, times(1)).updateStudentCourses(studentCourse);
  }

  @Test
  void 受講生詳細の論理削除機能＿リポジトリの処理が適切に呼び出せていること() {
    long id = student.getId();
    when(repository.findById(id)).thenReturn(student);

    sut.delete(id);

    verify(repository, times(1)).findById(id);
    verify(repository, times(1)).updateIsDeleted(id, true);
  }
}
