package com.guilherme.library.api.category;

import java.util.List;

import com.guilherme.library.auth.roles.annotations.RoleUser;
import com.guilherme.library.base.BaseController;
import com.guilherme.library.base.annotations.RestConfig;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@RestConfig
@Api(tags = "Categories")
@RequestMapping("/categories")
public class CategoryController extends BaseController<Category, CategoryRepository, CategoryService> {

  @GetMapping
  @RoleUser
  public ResponseEntity<List<Category>> findAll() {
    return ResponseEntity
      .ok()
      .body(service.findAll());
  }
}
