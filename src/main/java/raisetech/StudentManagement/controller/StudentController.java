package raisetech.StudentManagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.exception.OriginalException.OriginalIdException;
import raisetech.StudentManagement.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして実行されるController
 */
@RestController
@Validated
public class StudentController {
  private StudentService service;


  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @Operation(summary = "一覧検索", description = "受講生情報の一覧を検索します")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentDetail();
   }

   @Operation(summary = "単一検索", description = "単一の受講生情報を検索します")
   @GetMapping("/student/{id}")
   public StudentDetail studentDetailFindById (@PathVariable  Integer id) throws OriginalIdException {
    try {
      return service.studentDetailFindById(id);
    } catch (Exception e) {
      throw new OriginalIdException("入力値が不正です！");
    }
   }

   @Operation(summary = "受講生登録", description = "1件の受講生情報を登録します")
   @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody @Valid StudentDetail studentDetail) {
    StudentDetail registerStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(registerStudentDetail);
   }

   @Operation(summary = "受講生更新", description = "受講生情報を更新します")
   @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@Parameter(description = "受講生詳細") @RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました！");
   }
}
