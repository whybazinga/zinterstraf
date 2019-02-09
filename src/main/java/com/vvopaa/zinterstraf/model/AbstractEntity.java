package com.vvopaa.zinterstraf.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
abstract class AbstractEntity {
  @Id
  private String id;

}
