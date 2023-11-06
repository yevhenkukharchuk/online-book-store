package yevhen.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import yevhen.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(Long id);
}
