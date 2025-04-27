package raisetech.studentManagement.domain;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;

@Getter
@Setter
@NoArgsConstructor
public class StudentDetail {
  private Student student;
  private List<StudentsCourses> studentsCourses;
}
