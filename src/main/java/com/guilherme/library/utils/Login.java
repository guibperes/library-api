package com.guilherme.library.utils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Login {

  @NotBlank
  @Email
  @Size(min = 4, max = 40)
  private String email;

  @NotBlank
  @Size(min = 6, max = 60)
  private String password;
}
