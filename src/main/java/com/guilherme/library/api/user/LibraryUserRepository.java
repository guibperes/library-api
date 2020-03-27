package com.guilherme.library.api.user;

import java.util.Optional;

import com.guilherme.library.base.BaseRepository;

public interface LibraryUserRepository extends BaseRepository<LibraryUser> {

  public Optional<LibraryUser> findByToken(String token);

  public Optional<LibraryUser> findByEmail(String email);
}
