package raisetech.studentManagement.handler;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleAllExceptions(MethodArgumentNotValidException ex) {
    List<FieldError> errors = ex.getBindingResult().getFieldErrors();
    if (!errors.isEmpty()) {
      return ResponseEntity.badRequest().body(errors.get(0).getDefaultMessage());
    }
    return ResponseEntity.badRequest().body("入力内容に誤りがあります。");
  }
}
