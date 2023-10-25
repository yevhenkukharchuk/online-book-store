package yevhen.bookstore.service;

import java.util.List;
import yevhen.bookstore.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
