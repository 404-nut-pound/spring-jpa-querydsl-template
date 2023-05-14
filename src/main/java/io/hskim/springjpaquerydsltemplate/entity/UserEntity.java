package io.hskim.springjpaquerydsltemplate.entity;

import io.hskim.springjpaquerydsltemplate.api.user.dto.UserDto.UserResponseDto;
import io.hskim.springjpaquerydsltemplate.config.JpaConfig;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "aa_user")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter(value = AccessLevel.PROTECTED)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class UserEntity extends AuditEntity {

  @Id
  @Column(length = 20, nullable = false)
  @Comment(value = "사용자 ID")
  private String userId;

  @Column(length = 100, nullable = false)
  @Comment(value = "비밀번호")
  private String password;

  @Column(length = 50, nullable = false)
  @Comment(value = "사용자 이름")
  private String userName;

  @Column(length = 100, nullable = false, unique = true)
  @Comment(value = "이메일")
  private String email;

  @Builder.Default
  @Setter
  @Column(length = 1)
  @ColumnDefault(value = "'N'")
  @Comment(value = "삭제 여부")
  @Convert(converter = JpaConfig.class)
  private boolean deleteYn = false;

  public void setUserName(String userName) {
    if (StringUtils.hasText(userName)) {
      this.userName = userName;
    }
  }

  public void setEmail(String email) {
    if (StringUtils.hasText(email)) {
      this.email = email;
    }
  }

  public void setPassword(String password) {
    if (StringUtils.hasText(password)) {
      this.password = password;
    }
  }

  public UserResponseDto toDto() {
    return UserResponseDto
      .builder()
      .userId(this.userId)
      .userName(this.userName)
      .email(this.email)
      .deleteYn(this.deleteYn)
      .build();
  }
}
