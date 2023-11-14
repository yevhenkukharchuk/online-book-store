package yevhen.bookstore.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import yevhen.bookstore.dto.UserRegistrationRequestDto;

public class FieldMatcher implements ConstraintValidator<FieldMatch, UserRegistrationRequestDto> {
    @Override
    public boolean isValid(UserRegistrationRequestDto requestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return requestDto.password() != null
                && requestDto.password().equals(requestDto.repeatPassword());
    }
}
