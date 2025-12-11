package raisetech.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter
@EqualsAndHashCode
public class StudentCourse {
  private Integer id;
  private Integer studentId;
  private String courseName;
  private LocalDate startDate;
  private LocalDate endDate;
}
