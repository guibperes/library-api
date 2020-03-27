package com.guilherme.library.exceptions.errors;

import com.guilherme.library.exceptions.BaseException;

import org.springframework.http.HttpStatus;

public class AllBooksBorrowedError extends BaseException {

  private static final long serialVersionUID = 1L;

  public AllBooksBorrowedError(String message) {
    super(HttpStatus.CONFLICT, message);
  }
}
