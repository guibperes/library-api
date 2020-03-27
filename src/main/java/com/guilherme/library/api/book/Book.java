package com.guilherme.library.api.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.guilherme.library.api.category.Category;
import com.guilherme.library.api.user.LibraryUser;
import com.guilherme.library.base.BaseEntity;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Where(clause = "deleted=false")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Book extends BaseEntity {

  @NotBlank
  @Size(min = 4, max = 40)
  private String name;

  @NotBlank
  @Size(min = 0, max = 255)
  private String description;

  @NotNull
  @Min(0)
  private Integer pages;

  @NotNull
  @Min(1)
  private Integer quantity;

  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToMany
  @JsonManagedReference
  private List<LibraryUser> borrowedTo = new ArrayList<>();
}
