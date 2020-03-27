package com.guilherme.library.exceptions;

import com.guilherme.library.exceptions.errors.AllBooksBorrowedError;
import com.guilherme.library.exceptions.errors.BadRequestError;
import com.guilherme.library.exceptions.errors.EntityIdAlreadyExistsError;
import com.guilherme.library.exceptions.errors.EntityNotFoundedError;
import com.guilherme.library.exceptions.errors.UserAlreadyBorrowedBookError;
import com.guilherme.library.exceptions.errors.UserEmailExistsError;
import com.guilherme.library.exceptions.errors.UserIncorrectPasswordError;
import com.guilherme.library.exceptions.errors.UserNotBorrowedBookError;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(EntityNotFoundedError.class)
  public ResponseEntity<EntityNotFoundedError> handleEntityNotFoundedError(EntityNotFoundedError error) {
    return ResponseEntity
      .status(error.getStatus())
      .body(error);
  }

  @ExceptionHandler(EntityIdAlreadyExistsError.class)
  public ResponseEntity<EntityIdAlreadyExistsError> handleEntityIdAlreadyExistsError(EntityIdAlreadyExistsError error) {
    return ResponseEntity
      .status(error.getStatus())
      .body(error);
  }

  @ExceptionHandler(BadRequestError.class)
  public ResponseEntity<BadRequestError> handleBadRequestError(BadRequestError error) {
    return ResponseEntity
      .status(error.getStatus())
      .body(error);
  }

  @ExceptionHandler(UserEmailExistsError.class)
  public ResponseEntity<UserEmailExistsError> handleUserEmailExistsError(UserEmailExistsError error) {
    return ResponseEntity
      .status(error.getStatus())
      .body(error);
  }

  @ExceptionHandler(UserIncorrectPasswordError.class)
  public ResponseEntity<UserIncorrectPasswordError> handleUserIncorrectPasswordError(UserIncorrectPasswordError error) {
    return ResponseEntity
      .status(error.getStatus())
      .body(error);
  }

  @ExceptionHandler(AllBooksBorrowedError.class)
  public ResponseEntity<AllBooksBorrowedError> handleAllBooksBorrowedError(AllBooksBorrowedError error) {
    return ResponseEntity
      .status(error.getStatus())
      .body(error);
  }

  @ExceptionHandler(UserAlreadyBorrowedBookError.class)
  public ResponseEntity<UserAlreadyBorrowedBookError> handleUserAlreadyBorrowedBookError(UserAlreadyBorrowedBookError error) {
    return ResponseEntity
      .status(error.getStatus())
      .body(error);
  }

  @ExceptionHandler(UserNotBorrowedBookError.class)
  public ResponseEntity<UserNotBorrowedBookError> handleUserNotBorrowedBookError(UserNotBorrowedBookError error) {
    return ResponseEntity
      .status(error.getStatus())
      .body(error);
  }
}
