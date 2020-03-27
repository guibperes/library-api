package com.guilherme.library.api.user;

import java.util.Optional;
import java.util.UUID;

import com.guilherme.library.auth.roles.Role;
import com.guilherme.library.base.annotations.TransactionalService;
import com.guilherme.library.exceptions.errors.EntityNotFoundedError;
import com.guilherme.library.exceptions.errors.UserEmailExistsError;
import com.guilherme.library.exceptions.errors.UserIncorrectPasswordError;
import com.guilherme.library.utils.Login;
import com.guilherme.library.utils.PasswordUpdate;
import com.guilherme.library.utils.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@TransactionalService
public class LibraryUserService {

  @Autowired
  private LibraryUserRepository repo;

  @Autowired
  private BCryptPasswordEncoder crypt;

  public Optional<LibraryUser> findByToken(String token) {
    return repo.findByToken(token);
  }

  public LibraryUser findById(UUID id) {
    return repo
      .findById(id)
      .orElseThrow(() -> new EntityNotFoundedError("Cannot find user with the provided id"));
  }

  public String register(LibraryUser user) {
    Optional<LibraryUser> isEmailExists = repo.findByEmail(user.getEmail());

    if (isEmailExists.isPresent()) {
      throw new UserEmailExistsError("This email is already in use");
    }

    user
      .getRoles()
      .add(Role.ROLE_USER.name());

    user.setPassword(crypt.encode(user.getPassword()));
    user.setToken(Token.generateToken());

    return repo
      .save(user)
      .getToken();
  }

  public LibraryUser updateUser(LibraryUser user) {
    Optional<LibraryUser> userExists = repo.findById(user.getId());

    if (!userExists.isPresent()) {
      throw new EntityNotFoundedError("Cannot find user with the provided id");
    }

    LibraryUser oldUser = userExists.get();

    user.setRoles(oldUser.getRoles());
    user.setPassword(oldUser.getPassword());
    user.setToken(oldUser.getToken());

    return repo.save(user);
  }

  public void updatePassword(LibraryUser user, PasswordUpdate passwordUpdate) {
    if (!crypt.matches(passwordUpdate.getOldPassword(), user.getPassword())) {
      throw new UserIncorrectPasswordError("Incorrect password for this user");
    }

    user.setPassword(crypt.encode(passwordUpdate.getNewPassword()));
    repo.save(user);
  }

  public String login(Login login) {
    Optional<LibraryUser> userOptional = repo.findByEmail(login.getEmail());

    if (!userOptional.isPresent()) {
      throw new EntityNotFoundedError("Cannot find user with the provided email");
    }

    LibraryUser user = userOptional.get();

    if (!crypt.matches(login.getPassword(), user.getPassword())) {
      throw new UserIncorrectPasswordError("Incorrect password for this user");
    }

    user.setToken(Token.generateToken());

    return repo
      .save(user)
      .getToken();
  }
}
