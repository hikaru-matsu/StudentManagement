package raisetech.studentManagement.data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Student {
  @NotNull
  private Integer id;
  private String name;
  @Min(18)
  private Integer age;
  private String kanaName;
  private String nickname;
  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
  private String email;
  private String region;
  private String gender;
  private String remark;
  private Boolean isdeleted;
}
