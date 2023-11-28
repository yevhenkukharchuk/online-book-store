package yevhen.bookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import yevhen.bookstore.validation.Email;
import yevhen.bookstore.validation.FieldMatch;

@FieldMatch(field = "password",
            fieldMatch = "repeatPassword",
            message = "Password and repeated password must be equal")
public record UserRegistrationRequestDto(
        @NotBlank
        @Email
        @Size(max = 20)
        String email,
        @NotBlank
        @Size(min = 4, max = 80)
        String password,
        @NotBlank
        @Size(min = 4, max = 80)
        String repeatPassword,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String shippingAddress) {
}
