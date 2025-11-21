package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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
  void registerStudent(Student student);
}
