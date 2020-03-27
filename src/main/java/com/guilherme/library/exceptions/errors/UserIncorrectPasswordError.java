package com.guilherme.library.exceptions.errors;

import com.guilherme.library.exceptions.BaseException;

import org.springframework.http.HttpStatus;

public class UserIncorrectPasswordError extends BaseException {

  private static final long serialVersionUID = 1L;

  public UserIncorrectPasswordError(String message) {
    super(HttpStatus.FORBIDDEN, message);
  }
}
