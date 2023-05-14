package io.hskim.springjpaquerydsltemplate.api.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AuditDto {

  @Builder.Default
  private String createdBy = "";

  @Builder.Default
  private String createdAt = "";

  @Builder.Default
  private String updatedBy = "";

  @Builder.Default
  private String updatedAt = "";
}
