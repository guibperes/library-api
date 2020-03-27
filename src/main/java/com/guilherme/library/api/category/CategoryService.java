package com.guilherme.library.api.category;

import java.util.List;

import com.guilherme.library.base.BaseService;
import com.guilherme.library.base.annotations.TransactionalService;

@TransactionalService
public class CategoryService extends BaseService<Category, CategoryRepository> {

  public List<Category> findAll() {
    return repo.findAll();
  }
}
