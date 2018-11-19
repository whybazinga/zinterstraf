package com.vvopaa.zinterstraf.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
abstract class AuditEntity extends AbstractEntity {
  @CreatedDate
  private Date created;

  @CreatedBy
  private String createdBy;

  @LastModifiedDate
  private Date updated;

  @LastModifiedBy
  private String updatedBy;
}
