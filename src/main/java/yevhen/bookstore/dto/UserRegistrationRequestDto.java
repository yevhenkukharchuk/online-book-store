package yevhen.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import yevhen.bookstore.validation.FieldMatch;

@FieldMatch
public record UserRegistrationRequestDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 4, max = 50)
        String password,
        @NotBlank
        @Size(min = 4, max = 50)
        String repeatPassword,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String shippingAddress) {
}
