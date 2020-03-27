package com.guilherme.library.api.book;

import java.util.UUID;

import com.guilherme.library.api.user.LibraryUser;
import com.guilherme.library.api.user.LibraryUserService;
import com.guilherme.library.base.BaseService;
import com.guilherme.library.base.annotations.TransactionalService;
import com.guilherme.library.exceptions.errors.AllBooksBorrowedError;
import com.guilherme.library.exceptions.errors.UserAlreadyBorrowedBookError;
import com.guilherme.library.exceptions.errors.UserNotBorrowedBookError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@TransactionalService
public class BookService extends BaseService<Book, BookRepository> {

  @Autowired
  private LibraryUserService userService;

  public Page<Book> search(String query, String category, Pageable pageable) {
    return repo.search(query, category, pageable);
  }

  public void reserve(UUID userId, UUID bookId) {
    LibraryUser user = userService.findById(userId);
    Book book = this.findById(bookId);

    if (book.getBorrowedTo().size() >= book.getQuantity()) {
      throw new AllBooksBorrowedError("All books are borrowed");
    }

    if (book.getBorrowedTo().contains(user)) {
      throw new UserAlreadyBorrowedBookError("Book is already borrowed to this user");
    }

    book
      .getBorrowedTo()
      .add(user);

    user
      .getBorrowedBooks()
      .add(book);

    userService.updateUser(user);
    this.updateById(book);
  }

  public void devolve(UUID userId, UUID bookId) {
    LibraryUser user = userService.findById(userId);
    Book book = this.findById(bookId);

    if (!book.getBorrowedTo().contains(user)) {
      throw new UserNotBorrowedBookError("Book is not borrowed to this user");
    }

    book
      .getBorrowedTo()
      .remove(user);

    user
      .getBorrowedBooks()
      .remove(book);

    userService.updateUser(user);
    this.updateById(book);
  }
}
