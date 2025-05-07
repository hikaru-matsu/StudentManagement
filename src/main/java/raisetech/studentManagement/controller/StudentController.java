package raisetech.studentManagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentManagement.controller.converter.StudentConverter;
import raisetech.studentManagement.domain.StudentDetail;
import raisetech.studentManagement.handler.TestException;
import raisetech.studentManagement.service.StudentService;


@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生詳細一覧検索
   * @return　受講生一覧（全件）
   */
  @Tag(name = "受講生一覧検索")
  @Operation(summary = "一覧検索", description = "受講生の一覧を検索します。")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }
  /**
   * 受講生検索です。
   * IDに紐づく任意の受講生情報を取得します。
   * @return　受講生（1件）
   */
  @Tag(name = "受講生単一検索")
  @Operation(summary = "受講生単一検索", description = "受講生の情報を一件文取得します。")
  @GetMapping("/student/{id}")
  public StudentDetail findById(@PathVariable long id) {
    return service.findDetailById(id);
  }
  /**
   * 受講生詳細の更新を行います。
   * @param studentDetail　受講生詳細
   */
  @Tag(name = "受講生更新")
  @Operation(summary = "受講生更新", description = "受講生の情報を更新します。")
  @Validated
  @PutMapping("/updateStudent/{id}")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理がせいこうしました。");
  }

  /**
   * 受講生詳細の登録を行います。
   * 受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値を設定します。
   * @param studentDetail
   * @return　実行結果
   */
  @Tag(name = "受講生新規登録")
  @Operation(summary = "受講生登録", description = "受講生を登録します")
  @Validated
  @PostMapping("/registerStudent")
  public ResponseEntity<String> registerStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.registerStudent(studentDetail);
    return ResponseEntity.ok("登録処理がせいこうしました。");
  }

  /**
   * 受講生詳細の削除を行います。（論理削除）
   * @param id
   */
  @Tag(name = "受講生削除")
  @Operation(summary = "受講生削除", description = "受講生の情報を論理削除します。")
  @PatchMapping("/deleteStudent/{id}")
    public ResponseEntity<String> delete(long id) {
      service.delete(id);
      return ResponseEntity.ok("削除が成功しました");
  }
}
