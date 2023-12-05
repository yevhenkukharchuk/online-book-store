package yevhen.bookstore.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import yevhen.bookstore.dto.orderitem.OrderItemDto;
import yevhen.bookstore.model.Order.Status;

public record OrderDto(
        Long id,
        Long userId,
        List<OrderItemDto> orderItems,
        LocalDateTime orderDate,
        BigDecimal total,
        Status status) {
}
