package com.vvopaa.zinterstraf.payload;

import lombok.Data;

@Data
public class JwtAuthResponse {
  private String accessToken;
  private String tokenType = "Bearer";

  public JwtAuthResponse(String accessToken) {
    this.accessToken = accessToken;
  }
}
