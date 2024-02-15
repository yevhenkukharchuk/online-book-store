package yevhen.bookstore.service.shoppingcart;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yevhen.bookstore.dto.cartitem.CartItemDto;
import yevhen.bookstore.dto.cartitem.CartItemRequestDto;
import yevhen.bookstore.dto.shoppingcart.ShoppingCartDto;
import yevhen.bookstore.dto.shoppingcart.UpdateCartItemRequestDto;
import yevhen.bookstore.exception.EntityNotFoundException;
import yevhen.bookstore.mapper.CartItemMapper;
import yevhen.bookstore.mapper.ShoppingCartMapper;
import yevhen.bookstore.model.CartItem;
import yevhen.bookstore.model.ShoppingCart;
import yevhen.bookstore.model.User;
import yevhen.bookstore.repository.BookRepository;
import yevhen.bookstore.repository.CartItemRepository;
import yevhen.bookstore.repository.ShoppingCartRepository;
import yevhen.bookstore.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartMapper shoppingCartMapper;

    @Transactional
    @Override
    public ShoppingCartDto getShoppingCart() {
        return shoppingCartMapper.toDto(getCurrentCart());
    }

    @Transactional
    @Override
    public CartItemDto addCartItem(CartItemRequestDto requestDto) {
        CartItem cartItem = new CartItem();
        cartItem.setBook(bookRepository.findById(requestDto.bookId()).orElseThrow(()
                -> new EntityNotFoundException("Can't get book by id:" + requestDto.bookId())));
        cartItem.setQuantity(requestDto.quantity());
        cartItem.setShoppingCart(getCurrentCart());
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Transactional
    @Override
    public CartItemDto updateCartItemById(UpdateCartItemRequestDto requestDto, Long id) {
        ShoppingCart currentCart = getCurrentCart();
        CartItem cartItemFromDB = cartItemRepository.findByIdAndShoppingCartId(id,
                        currentCart.getId())
                .orElseThrow(() -> new EntityNotFoundException("Can't get cart item by id:" + id));
        cartItemFromDB.setQuantity(requestDto.quantity());
        return cartItemMapper.toDto(cartItemFromDB);
    }

    @Override
    public void deleteCartItemById(Long id) {
        ShoppingCart currentCart = getCurrentCart();
        cartItemRepository.findByIdAndShoppingCartId(id, currentCart.getId())
                .orElseThrow(() -> new EntityNotFoundException("Can't get cart item by id:" + id));
        cartItemRepository.deleteById(id);
    }

    private ShoppingCart getCurrentCart() {
        User currentUser = userRepository.findUserByEmail(SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName())
                .orElseThrow(() -> new EntityNotFoundException("Can't get "
                        + " current user"));
        return shoppingCartRepository.findByUserId(currentUser.getId())
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart();
                    cart.setUser(currentUser);
                    return shoppingCartRepository.save(cart);
                });
    }
}
