package yevhen.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yevhen.bookstore.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
