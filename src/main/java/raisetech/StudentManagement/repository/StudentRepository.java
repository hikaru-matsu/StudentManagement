package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

@Mapper
public interface StudentRepository {
  @Select("Select * From students Where is_deleted = false")
  List<Student> search();

  @Select("Select * From students_courses")
  List<StudentCourse> searchStudentCourse();

  @Select("Select * From students Where id = #{id}")
  Student studentFindById(Integer id);

  @Select("Select * From students_courses Where student_id = #{studentId}")
  List<StudentCourse> studentCourseFindById(Integer studentId);

  @Insert("Insert Into students(name, kana_name, nickname, email, area, age, gender, remark) Values(#{name}, #{kanaName}, #{nickname}, #{email}, #{area}, #{age}, #{gender}, #{remark})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  @Insert("Insert Into students_courses(student_id, course_name, start_date) Values(#{studentId}, #{courseName}, now())")
  void registerStudentCourse(StudentCourse studentCourse);

  @Update("Update students Set name = #{name}, kana_name = #{kanaName}, nickname = #{nickname}, email = #{email}, area = #{area}, age = #{age}, gender = #{gender}, remark = #{remark} Where id = #{id}")
  void updateStudent(Student student);

  @Update("Update students_courses Set course_name = #{courseName} Where id = #{id}")
  void updateStudentCourse(StudentCourse studentCourse);

  @Delete("Update students Set is_deleted = true Where id = #{id}")
  void deleteStudent(Integer id);
}
