package yevhen.bookstore.dto.order;

import jakarta.validation.constraints.NotBlank;
import yevhen.bookstore.model.Order.Status;

public record UpdateOrderDto(
        @NotBlank
        Status status) {
}
