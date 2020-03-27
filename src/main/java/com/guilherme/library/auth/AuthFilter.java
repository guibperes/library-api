package com.guilherme.library.auth;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guilherme.library.api.user.LibraryUser;
import com.guilherme.library.api.user.LibraryUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthFilter extends OncePerRequestFilter {

  @Autowired
  private LibraryUserService userService;

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
    String token = req.getHeader("Token");

    if (token != null && !token.isEmpty()) {
      Optional<LibraryUser> user = userService.findByToken(token);

      if (user.isPresent()) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());

        auth
          .setDetails(new WebAuthenticationDetailsSource()
          .buildDetails(req));

        SecurityContextHolder
          .getContext()
          .setAuthentication(auth);
      }
    }

    chain.doFilter(req, res);
  }
}
