package raisetech.studentManagement;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
public class StudentsCourses {
  private int id;
  private int studentId;
  private String courseName;
  private LocalDate startDate;
  private LocalDate endDate;
}
