package com.guilherme.library.exceptions.errors;

import com.guilherme.library.exceptions.BaseException;

import org.springframework.http.HttpStatus;

public class EntityNotFoundedError extends BaseException {

  private static final long serialVersionUID = 1L;

  public EntityNotFoundedError(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
}
