package com.vvopaa.zinterstraf.payload;

import lombok.Data;

@Data
public class ApisResponse {
  private Boolean success;
  private String message;

  public ApisResponse(Boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public ApisResponse(String message) {
    this.success = true;
    this.message = message;
  }

  public ApisResponse() {
    this.success = true;
    this.message = "Operation is done Successfully!";
  }
}
