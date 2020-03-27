package com.guilherme.library.exceptions;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
@JsonIgnoreProperties({"cause", "localizedMessage", "stackTrace", "suppressed"})
public abstract class BaseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final LocalDateTime timestamp = LocalDateTime.now();
  private int status;
  private String message;
  private String error;

  public BaseException(HttpStatus status, String message) {
		this.status = status.value();
		this.error = status.toString();
		this.message = message;
	}
}
