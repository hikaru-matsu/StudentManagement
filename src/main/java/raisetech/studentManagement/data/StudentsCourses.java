package raisetech.studentManagement.data;

import java.time.LocalDate;
import lombok.Getter;

@Getter

public class StudentsCourses {
  private int id;
  private int studentId;
  private String courseName;
  private LocalDate startDate;
  private LocalDate endDate;
}
