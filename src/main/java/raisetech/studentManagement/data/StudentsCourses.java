package raisetech.studentManagement.data;

import java.time.LocalDate;
import java.util.Date;
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
  private Date startDate;
  private LocalDate endDate;
}
