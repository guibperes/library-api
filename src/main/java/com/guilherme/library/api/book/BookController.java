package com.guilherme.library.api.book;

import java.util.UUID;

import com.guilherme.library.api.user.LibraryUser;
import com.guilherme.library.auth.roles.annotations.RoleUser;
import com.guilherme.library.base.BaseController;
import com.guilherme.library.base.annotations.RestConfig;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;

@RestConfig
@Api(tags = "Books")
@RequestMapping("/books")
public class BookController extends BaseController<Book, BookRepository, BookService> {

  private LibraryUser getContextUser() {
    return (LibraryUser) SecurityContextHolder
      .getContext()
      .getAuthentication()
      .getPrincipal();
  }

  @GetMapping
  @RoleUser
  public ResponseEntity<Page<Book>> search(
    @RequestParam(defaultValue = "name") String sort,
		@RequestParam(defaultValue = "asc") String order,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int limit,
		@RequestParam(defaultValue = "%") String category,
		@RequestParam(defaultValue = "%") String query
  ) {
    Pageable pageable = PageRequest.of(
      page,
      limit,
      Sort.by(Sort.Direction.fromString(order), sort)
    );

    return ResponseEntity
      .ok()
      .body(service.search(query, category, pageable));
  }

  @PostMapping("/reserve/{id}")
  @RoleUser
  public ResponseEntity<Void> reserve(@PathVariable UUID id) {
    LibraryUser user = this.getContextUser();
    service.reserve(user.getId(), id);

    return ResponseEntity
      .ok()
      .build();
  }

  @PostMapping("/devolve/{id}")
  @RoleUser
  public ResponseEntity<Void> devolve(@PathVariable UUID id) {
    LibraryUser user = this.getContextUser();
    service.devolve(user.getId(), id);

    return ResponseEntity
      .ok()
      .build();
  }
}
