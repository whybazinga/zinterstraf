package com.vvopaa.zinterstraf.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {
  @NotBlank
  @Size(max = 40)
  @Email
  private String email;

  @NotBlank
  @Size(min = 4, max = 20)
  private String password;
}
