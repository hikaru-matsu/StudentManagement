package raisetech.StudentManagement;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentRepository {

  @Select("Select * From students")
  List<Student> search();

  @Select("Select * From students_courses")
  List<StudentCourse> searchStudentCourse();

}
