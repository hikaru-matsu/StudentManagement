package raisetech.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;
import raisetech.studentManagement.domain.StudentDetail;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> courseSearch();

  @Insert("INSERT INTO students (name, age, kanaName, nickname, email, region, gender, remark) VALUES(#{name}, #{age}, #{kanaName}, #{nickname}, #{email}, #{region}, #{gender}, #{remark})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertStudent(Student student);
}



