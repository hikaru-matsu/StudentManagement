package raisetech.StudentManagement.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
  private Integer id;
  @NotBlank(message="名前の入力は必須です")
  private String name;
  @NotBlank(message="フリガナの入力は必須です")
  private String kanaName;
  private String nickname;
  @Email(message = "メールアドレスの形式が不正です")
  private String email;
  private String area;
  private int age;
  private String gender;
  private String remark;
  private Boolean isDeleted;
}
