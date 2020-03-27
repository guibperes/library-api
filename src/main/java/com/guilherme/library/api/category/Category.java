package com.guilherme.library.api.category;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
public class Category extends BaseEntity {

  @NotBlank
  @Size(min = 4, max = 40)
  private String name;
}
