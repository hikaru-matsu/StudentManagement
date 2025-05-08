package raisetech.studentManagement.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;

@Schema(description = "受講生詳細")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetail {
  @Valid
  private Student student;
  private List<StudentCourse> studentCourseList;
}
