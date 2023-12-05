package yevhen.bookstore.mapper;

import java.math.BigDecimal;
import java.util.Set;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import yevhen.bookstore.dto.order.CreateOrderRequestDto;
import yevhen.bookstore.dto.order.OrderDto;
import yevhen.bookstore.model.CartItem;
import yevhen.bookstore.model.Order;
import yevhen.bookstore.model.ShoppingCart;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = OrderItemMapper.class
)
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    OrderDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", source = "shoppingCart.cartItems")
    @Mapping(target = "total", source = "shoppingCart.cartItems",
            qualifiedByName = "totalOrderPrice")
    Order toOrder(ShoppingCart shoppingCart, CreateOrderRequestDto requestDto);

    @AfterMapping
    default void setOrderToItems(@MappingTarget Order order) {
        order.getOrderItems()
                .forEach(item -> item.setOrder(order));
    }

    @Named("totalOrderPrice")
    default BigDecimal calculateTotalOrderPrice(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::getTotalItemPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalItemPrice(CartItem cartItem) {
        BigDecimal quantity = BigDecimal.valueOf(cartItem.getQuantity());
        return cartItem.getBook().getPrice().multiply(quantity);
    }
}
