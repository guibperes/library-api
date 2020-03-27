package com.guilherme.library.api.book;

import com.guilherme.library.base.BaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends BaseRepository<Book> {

  @Query(
    "SELECT b FROM Book AS b "
    + "WHERE unaccent(LOWER(b.name)) LIKE '%'||unaccent(LOWER(:query))||'%' "
    + "AND cast(category_id as text) LIKE :category"
  )
	Page<Book> search(@Param("query") String query, @Param("category") String category, Pageable pageable);
}
