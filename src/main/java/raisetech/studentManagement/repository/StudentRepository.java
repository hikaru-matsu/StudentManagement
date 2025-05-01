package raisetech.studentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.studentManagement.data.Student;
import raisetech.studentManagement.data.StudentCourse;

/**
 * 受講生テーブルと受講生コーステーブルと紐づくRepositoryです。
 */
@Mapper
public interface StudentRepository {

  /**
   * 受講生情報を全件取得します。
   * @return student
   */
  List<Student> search();

  /**
   * 受講生コース情報を全件取得します。
   * @return studentCourse
   */
  @Select("SELECT * FROM students_courses")
  List<StudentCourse> courseSearch();

  /**
   * 受講生を新規登録します。IDは自動採番をおこなう。
   * @param student　受講生情報
   */
  void registerStudent(Student student);

  /**
   * 受講生コース情報を新規登録します。IDに関しては自動採番します。
   * @param studentCourse　受講生コース情報
   */
  void registerStudentCourse(StudentCourse studentCourse);

  /**
   * 受講生を更新します。
   * @param student
   */
  void updateStudent(Student student);

  /**
   * 受講生のコース情報を更新します。
   * @param studentCourse
   */
  void updateStudentCourses(StudentCourse studentCourse);

  void updateIsDeleted(long id, boolean isdeleted);

  /**
   * 受講生の単一検索
   * @param id
   * @return 一件の受講生情報
   */
  Student findById(long id);

  /**
   * 受講生コース情報のIDを取得
   * @param studentId
   * @return
   */
  List<StudentCourse> findByStudentId(long studentId);
}

