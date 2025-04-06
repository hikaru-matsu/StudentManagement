package raisetech.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> courseSearch();

  @Insert("INSERT INTO students (name, age, kanaName, nickname, email, region, gender, remark) values (#{name}, #{age}, #{kanaName}, #{nickname}, #{email}, #{region}, #{gender}, #{remark})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertStudent(Student student);

  @Update("UPDATE students SET name = #{name}, age = #{age}, kanaName = #{kanaName}, nickname = #{nickname}, email = #{email}, region = #{region}, gender = #{gender}, remark = #{remark} WHERE id = #{id}")
  void renewStudent(Student student);

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findById(long id);
}

