package raisetech.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生")
@Getter
@Setter
@EqualsAndHashCode
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
