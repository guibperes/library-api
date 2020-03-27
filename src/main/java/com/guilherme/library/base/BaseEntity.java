package com.guilherme.library.base;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@Getter
public abstract class BaseEntity {

  @Id
  private UUID id;

  public BaseEntity() {
    this.id = UUID.randomUUID();
  }

  @CreationTimestamp
  @Column(updatable = false)
  @JsonIgnore
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @JsonIgnore
  private LocalDateTime updatedAt;

  @JsonIgnore
  @Setter
  private Boolean deleted = false;

  @JsonIgnore
  @Setter
  private LocalDateTime deletedAt;
}
