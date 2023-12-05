package yevhen.bookstore.service.order;

import java.util.List;
import org.springframework.data.domain.Pageable;
import yevhen.bookstore.dto.order.CreateOrderRequestDto;
import yevhen.bookstore.dto.order.OrderDto;
import yevhen.bookstore.dto.order.UpdateOrderDto;
import yevhen.bookstore.dto.orderitem.OrderItemDto;

public interface OrderService {
    OrderDto createOrder(CreateOrderRequestDto requestDto);

    List<OrderDto> getOrders(Pageable pageable);

    OrderDto updateStatus(Long id, UpdateOrderDto updateOrderDto);

    List<OrderItemDto> getItemsByOrderId(Pageable pageable, Long id);

    OrderItemDto getItemByOrderIdAndItemId(Long orderId, Long itemId);
}
