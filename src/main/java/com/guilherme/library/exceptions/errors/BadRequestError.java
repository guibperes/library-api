package com.guilherme.library.exceptions.errors;

import java.util.List;

import com.guilherme.library.exceptions.BaseException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class BadRequestError extends BaseException {

  private static final long serialVersionUID = 1L;

  public BadRequestError(Errors errors) {
    super(HttpStatus.BAD_REQUEST, handleErrorFields(errors.getFieldErrors()));
  }

  private static String handleErrorFields(List<FieldError> errors) {
    String errorsString = errors
      .stream()
      .map((error) -> "(Field=" + error.getField() + " on object " + error.getObjectName().substring(0, 1).toUpperCase() + error.getObjectName().substring(1) + "), ")
      .reduce((acc, error) -> acc + error)
      .get();

      return errorsString.substring(0, errorsString.length() - 2);
  }
}
