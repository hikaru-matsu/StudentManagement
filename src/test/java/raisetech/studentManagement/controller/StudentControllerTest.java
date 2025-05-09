package raisetech.studentManagement.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
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
  @Autowired
  private Validator validator;

  @MockitoBean
  private StudentService service;

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


  @Test
  void 受講生詳細の新規登録処理ができて空で返ってくること() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String json =  objectMapper.writeValueAsString(studentDetail);
    mockMvc.perform(MockMvcRequestBuilders.post("/registerStudent").content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    verify(service, times(1)).registerStudent(any());
  }

  @Test
  void 受講生詳細の更新ができて空で返ってくること() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    String json =  objectMapper.writeValueAsString(studentDetail);
    mockMvc.perform(MockMvcRequestBuilders.put("/updateStudent/{id}",99).content(json).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    verify(service, times(1)).updateStudent(any());
  }
  @ParameterizedTest
  @ValueSource(longs = {99})
  void 受講生詳細の削除ができてること(long id) throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.patch("/deleteStudent/{id}",id)).andExpect(content().string("削除が成功しました")).andExpect(status().isOk());
    verify(service,times(1)).delete(id);
  }

  @Test
  void 受講生詳細の受講生で名前が空の時に入力チェックにかかること() {
    student.setName("");
    Set<ConstraintViolation<Student>>violations = validator.validate(student);
    assertEquals(1,violations.size());
  }

  @Test
  void 受講生詳細の受講生でメールアドレスが適切出ない時に入力チェックがかかること() {
    student.setEmail("9999-99-99");
    Set<ConstraintViolation<Student>>violations = validator.validate(student);
    assertEquals(1,violations.size());
  }

  @Test
  void 受講生詳細の受講生で適切な値を入力した際に入力チェックにかからないこと() {
    Set<ConstraintViolation<Student>>violations = validator.validate(student);
    assertEquals(0,violations.size());
  }
}