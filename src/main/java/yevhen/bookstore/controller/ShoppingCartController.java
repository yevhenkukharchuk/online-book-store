package yevhen.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yevhen.bookstore.dto.cartitem.CartItemDto;
import yevhen.bookstore.dto.cartitem.CartItemRequestDto;
import yevhen.bookstore.dto.shoppingcart.ShoppingCartDto;
import yevhen.bookstore.dto.shoppingcart.UpdateCartItemRequestDto;
import yevhen.bookstore.service.shoppingcart.ShoppingCartService;

@Tag(name = "Shopping cart management", description = "Endpoints for managing shopping carts")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @Operation(summary = "Show user's shopping carts",
            description = "Retrieve user's shopping carts")
    @PreAuthorize("hasRole('USER')")
    public ShoppingCartDto getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }

    @PostMapping
    @Operation(summary = "Add book to the shopping cart",
            description = "Add book to the shopping cart")
    @PreAuthorize("hasRole('USER')")
    public CartItemDto addBookToShoppingCart(@RequestBody @Valid CartItemRequestDto requestDto) {
        return shoppingCartService.addCartItem(requestDto);
    }

    @PutMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Update quantity of a book",
            description = "Update quantity of a book in the shopping cart")
    @PreAuthorize("hasRole('USER')")
    public CartItemDto updateQuantity(@PathVariable @Positive Long cartItemId,
                               @RequestBody @Valid UpdateCartItemRequestDto requestDto) {
        return shoppingCartService.updateCartItemById(requestDto, cartItemId);
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Delete book from shopping cart",
            description = "Delete book from shopping cart")
    @PreAuthorize("hasRole('USER')")
    public void deleteCartItemById(@PathVariable @Positive Long cartItemId) {
        shoppingCartService.deleteCartItemById(cartItemId);
    }
}
