package com.guilherme.library.utils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PasswordUpdate {

  @NotBlank
  @Size(min = 6, max = 60)
  private String oldPassword;

  @NotBlank
  @Size(min = 6, max = 60)
  private String newPassword;
}
