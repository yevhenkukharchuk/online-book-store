package yevhen.bookstore.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yevhen.bookstore.model.Book;
import yevhen.bookstore.repository.BookRepository;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Book save(Book book) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
            return book;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save book:" + book, e);
        }
    }

    @Override
    public List findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        }
    }
}
