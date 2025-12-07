package raisetech.StudentManagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/studentList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));
    verify(service, times(1)).searchStudentDetail();
  }

  @Test
  void 受講生詳細の単一検索が実行できて一件の情報が返ってくること() {
    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();
    student.setId(999);
    student.setName("テスト太郎");
    student.setKanaName("テストタロウ");
    student.setNickname("テスト");
    student.setEmail("Test@example.com");
    studentCourse.setId(999);
    studentCourse.setStudentId(999);
    studentCourse.setCourseName("テスト");
    List<StudentCourse> studentCourseList = Arrays.asList(studentCourse);

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourse(studentCourseList);

    try {
      mockMvc.perform(MockMvcRequestBuilders.get("/student/999"))
          .andExpect(status().isOk());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    verify(service, times(1)).studentDetailFindById(999);
  }

  @Test
  void 受講生登録が実行できて一件の情報が登録できていること() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();

    student.setId(999);
    student.setName("テスト太郎");
    student.setKanaName("テストタロウ");
    student.setNickname("テスト");
    student.setEmail("Test@example.com");

    studentCourse.setId(999);
    studentCourse.setStudentId(999);
    studentCourse.setCourseName("テスト");
    List<StudentCourse> studentCourseList = Arrays.asList(studentCourse);

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourse(studentCourseList);

    String json = mapper.writeValueAsString(studentDetail);

    mockMvc.perform(post("/registerStudent").contentType(MediaType.APPLICATION_JSON).content(json));
  }

  @Test
  void 受講生更新が実行できて情報が更新されていること() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();

    student.setId(999);
    student.setName("テスト太郎");
    student.setKanaName("テストタロウ");
    student.setNickname("テスト");
    student.setEmail("Test@example.com");

    studentCourse.setId(999);
    studentCourse.setStudentId(999);
    studentCourse.setCourseName("テスト");
    List<StudentCourse> studentCourseList = Arrays.asList(studentCourse);

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourse(studentCourseList);

    String json = mapper.writeValueAsString(studentDetail);

    mockMvc.perform(post("/updateStudent").contentType(MediaType.APPLICATION_JSON).content(json));
  }

  @Test
  void 受講生詳細の受講生で名前とカナ名が未入力であったときに入力チェックがかかること() {
    Student student = new Student();
    student.setId(999);
    student.setName(" ");
    student.setKanaName("");
    student.setNickname("ヒカル");
    student.setEmail("Test@example.com");
    student.setArea("奈良県");
    student.setAge(26);
    student.setGender("男性");
    student.setRemark("");
    student.setIsDeleted(false);

    Set<ConstraintViolation<Student>> validations = validator.validate(student);

    assertThat(validations.size()).isEqualTo(2);
  }
}