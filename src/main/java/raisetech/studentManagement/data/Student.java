package raisetech.studentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "受講生情報")
@Getter
@Setter
@NoArgsConstructor
public class Student {
  @Schema(description = "ユーザーID", required = true)
  private Integer id;
  @Schema(description = "ユーザー名", required = true)
  @NotBlank(message = "ユーザー名は必須です")
  private String name;
  @Schema(description = "年齢", minimum = "18")
  @Min(18)
  private Integer age;
  @Schema(description = "カナ名", required = true)
  private String kanaName;
  @Schema(description = "ニックネーム")
  private String nickname;
  @Schema(description = "メールアドレス", required = true)
  @NotBlank(message = "メールアドレスは必須です。")
  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
  private String email;
  @Schema(description = "住所")
  private String region;
  @Schema(description = "性別")
  private String gender;
  @Schema(description = "備考" )
  private String remark;
  private Boolean isdeleted;

  public Student (int id, String name, String kanaName, String nickname, String email, String region, int age, String gender, String remark) {
    this.id = id;
    this.name = name;
    this.kanaName = kanaName;
    this.nickname = nickname;
    this.email = email;
    this.region = region;
    this.age = age;
    this.gender = gender;
    this.remark = remark;
  }
}
