package raisetech.studentManagement.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Student {
  private Integer id;
  private String name;
  private Integer age;
  private String kanaName;
  private String nickname;
  private String email;
  private String region;
  private String gender;
  private String remark;
  private Boolean isdeleted;
}
