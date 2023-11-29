package yevhen.bookstore.service.shoppingcart;

import yevhen.bookstore.dto.cartitem.CartItemDto;
import yevhen.bookstore.dto.cartitem.CartItemRequestDto;
import yevhen.bookstore.dto.shoppingcart.ShoppingCartDto;
import yevhen.bookstore.dto.shoppingcart.UpdateCartItemRequestDto;

public interface ShoppingCartService {
    ShoppingCartDto getShoppingCart();

    CartItemDto addCartItem(CartItemRequestDto requestDto);

    CartItemDto updateCartItemById(UpdateCartItemRequestDto requestDto, Long id);

    void deleteCartItemById(Long id);
}
