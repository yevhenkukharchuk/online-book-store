package yevhen.bookstore.dto.orderitem;

public record OrderItemDto(
        Long id,
        Long bookId,
        Integer quantity) {
}
