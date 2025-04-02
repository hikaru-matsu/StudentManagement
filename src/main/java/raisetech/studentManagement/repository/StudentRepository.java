package raisetech.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

  @Insert("INSERT INTO students_courses (student_id, course_name, start_date) VALUES(#{studentId}, #{courseName}, #{startDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertStudentCourse(StudentsCourses studentCourses);


  @Update("UPDATE students SET name=#{name}, age=#{age}, kanaName=#{kanaName}, email=#{email}, region=#{region}, gender=#{gender}, remark=#{remark} WHERE id=#{id}")
  void editStudent(Student student);

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findById(Long id);
}
