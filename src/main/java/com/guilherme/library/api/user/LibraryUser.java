package com.guilherme.library.api.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.guilherme.library.api.book.Book;
import com.guilherme.library.auth.UserAuth;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Where(clause = "deleted=false")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LibraryUser extends UserAuth {

	private static final long serialVersionUID = 1L;

  @NotBlank
  @Size(min = 4, max = 40)
  private String name;

  @NotBlank
  @Email
  @Size(min = 4, max = 40)
  @Column(unique = true)
  private String email;

  @ManyToMany(mappedBy = "borrowedTo")
  @JsonBackReference
  private List<Book> borrowedBooks = new ArrayList<>();
}
