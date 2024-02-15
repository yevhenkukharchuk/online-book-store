package yevhen.bookstore.service.order;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yevhen.bookstore.dto.order.CreateOrderRequestDto;
import yevhen.bookstore.dto.order.OrderDto;
import yevhen.bookstore.dto.order.UpdateOrderDto;
import yevhen.bookstore.dto.orderitem.OrderItemDto;
import yevhen.bookstore.exception.EntityNotFoundException;
import yevhen.bookstore.mapper.OrderItemMapper;
import yevhen.bookstore.mapper.OrderMapper;
import yevhen.bookstore.model.Order;
import yevhen.bookstore.model.ShoppingCart;
import yevhen.bookstore.model.User;
import yevhen.bookstore.repository.OrderItemRepository;
import yevhen.bookstore.repository.OrderRepository;
import yevhen.bookstore.repository.ShoppingCartRepository;
import yevhen.bookstore.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Transactional
    @Override
    public OrderDto createOrder(CreateOrderRequestDto requestDto) {
        User currentUser = getCurrentUser();
        ShoppingCart currentShoppingCart = getCurrentCart(currentUser);
        Order order = orderMapper.toOrder(currentShoppingCart, requestDto);
        currentShoppingCart.clear();
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Transactional
    @Override
    public List<OrderDto> getOrders(Pageable pageable) {
        User currentUser = getCurrentUser();
        return orderRepository.findAllByUserId(pageable, currentUser.getId()).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public OrderDto updateStatus(Long id, UpdateOrderDto updateOrderDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find order by id: " + id));
        order.setStatus(updateOrderDto.status());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Transactional
    @Override
    public List<OrderItemDto> getItemsByOrderId(Pageable pageable, Long id) {
        return orderItemRepository.findAllByOrderId(pageable, id).stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public OrderItemDto getItemByOrderIdAndItemId(Long orderId, Long itemId) {
        return orderItemRepository.findByIdAndOrderId(itemId, orderId)
                .map(orderItemMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Can't find order item by id:"
                        + itemId + " for order with id:" + orderId));
    }

    private User getCurrentUser() {
        return userRepository.findUserByEmail(SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName())
                .orElseThrow(() -> new EntityNotFoundException("Can't get "
                        + " current user"));
    }

    private ShoppingCart getCurrentCart(User user) {
        return shoppingCartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Can't get "
                        + " shopping cart for current user"));
    }
}
