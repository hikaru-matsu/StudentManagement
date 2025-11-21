package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

@Mapper
public interface StudentRepository {
  @Select("Select * From students")
  List<Student> search();

  @Select("Select * From students_courses")
  List<StudentCourse> searchStudentCourse();

  @Insert("Insert Into students(name, kana_name, nickname, email, area, age, gender, remark) Values(#{name}, #{kanaName}, #{nickname}, #{email}, #{area}, #{age}, #{gender}, #{remark})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  @Insert("Insert Into students_courses(student_id, course_name, start_date) Values(#{studentId}, #{courseName}, now())")
  void registerStudentCourse(StudentCourse studentCourse);
}
