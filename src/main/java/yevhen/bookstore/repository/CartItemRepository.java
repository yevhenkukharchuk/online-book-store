package yevhen.bookstore.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yevhen.bookstore.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByIdAndShoppingCartId(Long cartItemId, Long shoppingCartId);

    List<CartItem> findAllByBookId(Long bookId);
}
