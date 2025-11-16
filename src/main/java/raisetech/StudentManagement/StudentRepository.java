package raisetech.StudentManagement;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.DeleteMapping;


@Mapper
public interface StudentRepository {
  @Select("Select * From student Where name = #{name}")
  Student searchByName(String name);

  @Select("Select * From student")
  List<Student> searchStudentList();

  @Insert("Insert Into student Values(#{name}, #{age})")
  void registerStudent(String name, int age);

  @Update("Update student Set age = #{age} Where name = #{name}")
  void updateStudent(String name, int age);

  @Delete("Delete From student Where name = #{name}")
  void deleteStudent(String name);
}
