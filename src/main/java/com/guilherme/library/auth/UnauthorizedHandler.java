package com.guilherme.library.auth;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint, Serializable {

  private static final long serialVersionUID = 1L;

  @Override
  public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException err) throws IOException, ServletException {
    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized, you don't have permission to access");
  }
}
