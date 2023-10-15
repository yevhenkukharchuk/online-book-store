package yevhen.bookstore.repository.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import yevhen.bookstore.model.Book;
import yevhen.bookstore.repository.BookRepository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }
}
