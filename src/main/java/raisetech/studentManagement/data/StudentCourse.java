package raisetech.studentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter
@NoArgsConstructor
public class StudentCourse {
  @Schema(description = "受講コースID", required = true)
  private Integer id;
  @Schema(description = "受講生ID", required = true)
  private Integer studentId;
  @Schema(description = "受講コース名", required = true)
  private String courseName;
  @Schema(description = "受講開始日", required = true)
  private LocalDate startDate;
  @Schema(description = "受講終了日")
  private LocalDate endDate;
}
