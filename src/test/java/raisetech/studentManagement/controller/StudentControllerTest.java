package raisetech.studentManagement.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.fixture.TestData;
import raisetech.studentManagement.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  Student student;
  StudentCourse studentCourse;
  List<StudentCourse>studentCourseList;
  StudentDetail studentDetail;


  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private StudentService service;

  public static Stream<Arguments> studentDetailProvider() {
    Student student = TestData.testStudent();
    StudentCourse studentCourse = TestData.testStudentCourse();
    StudentDetail studentDetail = TestData.testStudentDetail();

    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(List.of(studentCourse));
    return Stream.of(Arguments.of(studentDetail));
  }

  @BeforeEach
  void setUp() {
    student = TestData.testStudent();
    studentCourse = TestData.testStudentCourse();
    studentDetail = TestData.testStudentDetail();

    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(List.of(studentCourse));
  }

  @Test
  void 受講生詳細の一覧検索が実行できて1件のデータが入ったリストが返ってくる() throws Exception {
    List<StudentDetail>studentDetailList = List.of(studentDetail);
    when(service.searchStudentList()).thenReturn(studentDetailList);
    mockMvc.perform(MockMvcRequestBuilders.get("/studentList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[{\"student\":{\"id\":99,\"name\":\"テスト太郎\",\"age\":100,\"kanaName\":\"テストタロウ\",\"nickname\":\"テスト君\",\"email\":\"Test@example.com\",\"region\":\"どこか\",\"gender\":\"不明\",\"remark\":\"これはテスト専用のデータです。\",\"isdeleted\":null},\"studentCourseList\":[{\"id\":99,\"studentId\":99,\"courseName\":\"テストコース\",\"startDate\":\"5555-05-05\",\"endDate\":\"9999-09-09\"}]}]"));

    verify(service, times(1)).searchStudentList();
  }

  @ParameterizedTest
  @ValueSource(longs = {99})
  void 受講生詳細の単一検索が実行できて一件のデータが返ってくる(long id) throws Exception {
    when(service.findDetailById(99)).thenReturn(studentDetail);
    mockMvc.perform(MockMvcRequestBuilders.get("/student/{id}", 99))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"student\":{\"id\":99,\"name\":\"テスト太郎\",\"age\":100,\"kanaName\":\"テストタロウ\",\"nickname\":\"テスト君\",\"email\":\"Test@example.com\",\"region\":\"どこか\",\"gender\":\"不明\",\"remark\":\"これはテスト専用のデータです。\",\"isdeleted\":null},\"studentCourseList\":[{\"id\":99,\"studentId\":99,\"courseName\":\"テストコース\",\"startDate\":\"5555-05-05\",\"endDate\":\"9999-09-09\"}]}"));
    verify(service, times(1)).findDetailById(id);
  }

  @ParameterizedTest
  @MethodSource("studentDetailProvider")
  void 受講生詳細の新規登録処理が実行できていること(StudentDetail studentDetail) throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/registerStudent"))
        .andExpect(status().isOk());
    verify(service, times(1)).registerStudent((studentDetail));
  }
}