package com.guilherme.library.utils;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Token {

  private String token;

  public static String generateToken() {
    String uuid = UUID
      .randomUUID()
      .toString()
      .replaceAll("-", "");

    String timestamp = Long
      .toHexString(new Date().getTime());

    return uuid + "z" + timestamp;
  }
}
