package raisetech.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentsCourses;

/**
 * 受講生テーブルと受講生コーステーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students WHERE isdeleted = false")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> courseSearch();

  @Insert("INSERT INTO students (name, age, kanaName, nickname, email, region, gender, remark, isdeleted) values (#{name}, #{age}, #{kanaName}, #{nickname}, #{email}, #{region}, #{gender}, #{remark}, false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  @Insert("INSERT INTO students_courses (student_id, course_name, start_date) values (#{studentId}, #{courseName}, NOW())")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentsCourses(StudentsCourses studentsCourses);

  @Update("UPDATE students SET name = #{name}, age = #{age}, kanaName = #{kanaName}, nickname = #{nickname}, email = #{email}, region = #{region}, gender = #{gender}, remark = #{remark}  WHERE id = #{id}")
  void updateStudent(Student student);

  @Update("UPDATE students SET isdeleted = #{isdeleted} WHERE id = #{id}")
  void updateIsDeleted(long id, boolean isdeleted);

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student findById(long id);

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourses> findByStudentId(long studentId);
}

