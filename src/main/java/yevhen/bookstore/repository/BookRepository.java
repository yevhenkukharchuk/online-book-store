package yevhen.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yevhen.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book getBookById(Long id);

}
