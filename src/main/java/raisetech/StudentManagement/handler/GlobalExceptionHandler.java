package raisetech.StudentManagement.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import raisetech.StudentManagement.exception.OriginalException.OriginalIdException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(OriginalIdException.class)
  public ResponseEntity<String> handleOriginalException(OriginalIdException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    StringBuilder errorMessage = new StringBuilder();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();
      errorMessage.append(fieldName).append(": ").append(message).append(". ");
    });
    return ResponseEntity.badRequest().body(errorMessage.toString());
  }

}
