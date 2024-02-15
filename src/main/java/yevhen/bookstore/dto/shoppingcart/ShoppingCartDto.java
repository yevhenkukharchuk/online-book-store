package yevhen.bookstore.dto.shoppingcart;

import java.util.List;
import yevhen.bookstore.dto.cartitem.CartItemDto;

public record ShoppingCartDto(
        Long id,
        Long userId,
        List<CartItemDto> cartItems) {
}
