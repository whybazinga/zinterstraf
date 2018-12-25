package com.vvopaa.zinterstraf.payload;

import lombok.Data;

@Data
public class ApisResponse {
  public static final String SUCCESS_RESPONSE = "Operation is successful!";
  public static final String ERROR_RESPONSE = "Operation is failed. Error has occurred!";

  private boolean success;
  private String message;

  public ApisResponse(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public ApisResponse(boolean success) {
    this.success = success;
    this.message = success ? SUCCESS_RESPONSE : ERROR_RESPONSE;
  }

}
