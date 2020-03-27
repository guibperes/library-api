package com.guilherme.library.api.user;

import javax.validation.Valid;

import com.guilherme.library.auth.roles.annotations.RoleUser;
import com.guilherme.library.base.annotations.RestConfig;
import com.guilherme.library.exceptions.errors.BadRequestError;
import com.guilherme.library.utils.Login;
import com.guilherme.library.utils.PasswordUpdate;
import com.guilherme.library.utils.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

@RestConfig
@Api(tags = "LibraryUser")
@RequestMapping("/users")
public class LibraryUserController {

  @Autowired
  private LibraryUserService service;

  private LibraryUser getContextUser() {
    return (LibraryUser) SecurityContextHolder
      .getContext()
      .getAuthentication()
      .getPrincipal();
  }

  @PostMapping
  public ResponseEntity<Token> register(@RequestBody @Valid LibraryUser user, @ApiIgnore Errors err) {
    if (err.hasErrors()) {
      throw new BadRequestError(err);
    }

    return ResponseEntity
      .ok()
      .body(new Token(service.register(user)));
  }

  @PostMapping("/login")
  public ResponseEntity<Token> login(@RequestBody @Valid Login login, @ApiIgnore Errors err) {
    if (err.hasErrors()) {
      throw new BadRequestError(err);
    }

    return ResponseEntity
      .ok()
      .body(new Token(service.login(login)));
  }

  @PutMapping
  @RoleUser
  public ResponseEntity<LibraryUser> updateUser(@RequestBody @Valid LibraryUser user, @ApiIgnore Errors err) {
    if (err.hasErrors()) {
      throw new BadRequestError(err);
    }

    return ResponseEntity
      .ok()
      .body(service.updateUser(user));
  }

  @PutMapping("/password")
  @RoleUser
  public ResponseEntity<Void> updatePassword(@RequestBody @Valid PasswordUpdate passwordUpdate, @ApiIgnore Errors err) {
    if (err.hasErrors()) {
      throw new BadRequestError(err);
    }

    LibraryUser user = this.getContextUser();
    service.updatePassword(user, passwordUpdate);

    return ResponseEntity
      .ok()
      .build();
  }

  @GetMapping
  @RoleUser
  public ResponseEntity<LibraryUser> get() {
    LibraryUser user = this.getContextUser();

    return ResponseEntity
      .ok()
      .body(service.findById(user.getId()));
  }
}
