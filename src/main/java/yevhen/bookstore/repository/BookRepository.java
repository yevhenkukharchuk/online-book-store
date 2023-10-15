package yevhen.bookstore.repository;

import java.util.List;
import yevhen.bookstore.model.Book;

public interface BookRepository {
    Book save(Book book);

    List findAll();
}
