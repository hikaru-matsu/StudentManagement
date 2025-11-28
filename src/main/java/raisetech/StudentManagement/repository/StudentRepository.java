package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourse;

/**
 * 受講生テーブルと受講生コーステーブルと紐づくRepository
 */
@Mapper
public interface StudentRepository {

  List<Student> searchStudent();

  List<StudentCourse> searchStudentCourse();

  Student studentFindById(Integer id);

  List<StudentCourse> studentCoursesFindById(Integer studentId);

  void registerStudent(Student student);

  void registerStudentCourse(StudentCourse studentCourse);

  void updateStudent(Student student);

  void updateStudentCourse(StudentCourse studentCourse);
}
