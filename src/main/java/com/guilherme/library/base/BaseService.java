package com.guilherme.library.base;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.guilherme.library.exceptions.errors.EntityIdAlreadyExistsError;
import com.guilherme.library.exceptions.errors.EntityNotFoundedError;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<
  ENTITY extends BaseEntity,
  REPOSITORY extends BaseRepository<ENTITY>
> {

  @Autowired
  protected REPOSITORY repo;

  private final String entityName = ((Class<?>) ((ParameterizedType)
    getClass()
    .getGenericSuperclass())
    .getActualTypeArguments()[0])
    .getSimpleName();

  public UUID save(ENTITY entity) {
    if (repo.existsById(entity.getId())) {
      throw new EntityIdAlreadyExistsError(entityName + " with the provided id already exists");
    }

    return repo
      .save(entity)
      .getId();
  }

  public UUID updateById(ENTITY entity) {
    if (!repo.existsById(entity.getId())) {
      throw new EntityNotFoundedError("Cannot find " + entityName + " with the provided id");
    }

    return repo
      .save(entity)
      .getId();
  }

  public void deleteById(UUID id) {
    Optional<ENTITY> entityOptional = repo.findById(id);

    if (!entityOptional.isPresent()) {
      throw new EntityNotFoundedError("Cannot find " + entityName + " with the provided id");
    }

    ENTITY entity = entityOptional.get();

    entity.setDeleted(true);
    entity.setDeletedAt(LocalDateTime.now());

    repo.save(entity);
  }

  public ENTITY findById(UUID id) {
    return repo
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundedError("Cannot find " + entityName + " with the provided id"));
  }
}
