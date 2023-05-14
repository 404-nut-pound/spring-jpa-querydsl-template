package io.hskim.springjpaquerydsltemplate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class AuditEntity {

  @CreatedBy
  @Column(length = 20, updatable = false, nullable = false)
  @Comment(value = "등록 사용자 계정")
  private String createdBy;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  @ColumnDefault(value = "now()")
  @Comment(value = "등록일시")
  private LocalDateTime createdAt;

  @LastModifiedBy
  @Column(length = 20, nullable = false)
  @Comment(value = "수정 사용자 계정")
  private String updatedBy;

  @LastModifiedDate
  @Column(nullable = false)
  @ColumnDefault(value = "now()")
  @Comment(value = "수정일시")
  private LocalDateTime updatedAt;
}
