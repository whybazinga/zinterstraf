package com.vvopaa.zinterstraf.model.special;

import lombok.Data;

@Data
public class Options {
  private String imagePath;
  private String jwtSecret;
  private int jwtExpirationMs;
}
