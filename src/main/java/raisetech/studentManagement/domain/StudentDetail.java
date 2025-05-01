package raisetech.studentManagement.domain;

import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {
  @Valid
  private Student student;
  private List<StudentCourse> studentCourseList;
}
