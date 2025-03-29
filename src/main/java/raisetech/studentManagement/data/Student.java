package raisetech.studentManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {

  private int id;
  private String name;
  private int age;
  private String furigana;
  private String nickname;
  private String email;
  private String region;
  private String gender;
  private String remark;
  private Boolean isdeleted;
}
