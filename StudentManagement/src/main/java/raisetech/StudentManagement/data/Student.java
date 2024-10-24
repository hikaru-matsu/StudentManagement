package raisetech.StudentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {

  private String id;
  private String name;
  private String kanaName;
  private String nickname;
  private String email;
  private int age;
  private String gender;
  private String area;
  private String remark;
  private Boolean isDeleated;
}
