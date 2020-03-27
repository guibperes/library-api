package com.guilherme.library.api.category;

import com.guilherme.library.base.BaseController;
import com.guilherme.library.base.annotations.RestConfig;

import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

@RestConfig
@Api(tags = "Categories")
@RequestMapping("/categories")
public class CategoryController extends BaseController<Category, CategoryRepository, CategoryService> {
}
