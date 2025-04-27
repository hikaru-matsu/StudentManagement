package raisetech.studentManagement.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentsCourses {
  private Integer id;
  private Integer studentId;
  private String courseName;
  private LocalDate startDate;
  private LocalDate endDate;
}
