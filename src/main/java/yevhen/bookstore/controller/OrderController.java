package yevhen.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import yevhen.bookstore.dto.order.CreateOrderRequestDto;
import yevhen.bookstore.dto.order.OrderDto;
import yevhen.bookstore.dto.order.UpdateOrderDto;
import yevhen.bookstore.dto.orderitem.OrderItemDto;
import yevhen.bookstore.service.order.OrderService;

@Tag(name = "Order management", description = "Endpoints for place and managing orders")
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Place an order",
               description = "Creating order according to current cart items")
    public OrderDto placeOrder(@RequestBody @Valid CreateOrderRequestDto requestDto) {
        return orderService.createOrder(requestDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get order's history", description = "Retrieve user's order history")
    public List<OrderDto> getOrders(Pageable pageable) {
        return orderService.getOrders(pageable);
    }

    @Operation(summary = "Update order status", description = "Update order status")
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public OrderDto updateOrderStatus(@Positive @PathVariable Long id,
                                      @RequestBody UpdateOrderDto updateOrderDto) {
        return orderService.updateStatus(id, updateOrderDto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/items")
    @Operation(summary = "Get order items by order id",
            description = "Retrieve all order items for a specific order")
    public List<OrderItemDto> getItemsByOrderId(Pageable pageable,
                                                @PathVariable @Positive Long id) {
        return orderService.getItemsByOrderId(pageable, id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Get a specific order item",
            description = "Retrieve a specific order item within an order")
    public OrderItemDto getItemByOrderIdAndItemId(@PathVariable @Positive Long orderId,
                                                @PathVariable @Positive Long itemId) {
        return orderService.getItemByOrderIdAndItemId(orderId, itemId);
    }
}
