package com.guilherme.library.base;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.guilherme.library.auth.roles.annotations.RoleAdmin;
import com.guilherme.library.auth.roles.annotations.RoleUser;
import com.guilherme.library.exceptions.errors.BadRequestError;
import com.guilherme.library.utils.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import springfox.documentation.annotations.ApiIgnore;

public abstract class BaseController<
  ENTITY extends BaseEntity,
  REPOSITORY extends BaseRepository<ENTITY>,
  SERVICE extends BaseService<ENTITY, REPOSITORY>
> {

  @Autowired
  protected SERVICE service;

  @PostMapping
  @RoleAdmin
  public ResponseEntity<Id> save(@RequestBody @Valid ENTITY entity, @ApiIgnore Errors err) {
    if (err.hasErrors()) {
      throw new BadRequestError(err);
    }

    return ResponseEntity
      .ok()
      .body(new Id(service.save(entity)));
  }

  @PutMapping
  @RoleAdmin
  public ResponseEntity<Id> updatedById(@RequestBody @Valid ENTITY entity, @ApiIgnore Errors err) {
    if (err.hasErrors()) {
      throw new BadRequestError(err);
    }

    return null;
  }

  @DeleteMapping("/{id}")
  @RoleAdmin
  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
    service.deleteById(id);

    return ResponseEntity
      .ok()
      .build();
  }

  @GetMapping
  @RoleUser
  public ResponseEntity<List<ENTITY>> findAll() {
    return ResponseEntity
      .ok()
      .body(service.findAll());
  }

  @GetMapping("/{id}")
  @RoleUser
  public ResponseEntity<ENTITY> findById(@PathVariable UUID id) {
    return ResponseEntity
      .ok()
      .body(service.findById(id));
  }
}
