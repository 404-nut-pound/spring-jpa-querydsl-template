package io.hskim.springjpaquerydsltemplate.api.user.dto;

import io.hskim.springjpaquerydsltemplate.api.common.dto.AuditDto;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class UserDto {

  @Getter
  @Setter
  @SuperBuilder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class UserSearchDto extends AuditDto {

    private String userId;

    private String userName;

    private String email;

    @Builder.Default
    @Parameter(name = "deleteYn")
    private List<Boolean> deleteYnList = new ArrayList<>();

    public void setDeleteYn(List<Boolean> deleteYnList) {
      this.deleteYnList = deleteYnList;
    }
  }

  @Getter
  @Setter
  @SuperBuilder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class UserRequestDto {

    private String userId;

    private String userName;

    private String email;

    private String password;

    private String passwordCheck;
  }

  @Getter
  @Setter
  @SuperBuilder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class UserResponseDto extends AuditDto {

    private String userId;

    private String userName;

    private String email;

    private boolean deleteYn;
  }
}
