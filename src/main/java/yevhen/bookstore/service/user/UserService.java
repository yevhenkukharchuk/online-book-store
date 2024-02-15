package yevhen.bookstore.service.user;

import yevhen.bookstore.dto.user.UserRegistrationRequestDto;
import yevhen.bookstore.dto.user.UserResponseDto;
import yevhen.bookstore.exception.RegistrationException;

public interface UserService {
    UserResponseDto registration(UserRegistrationRequestDto requestDto)
            throws RegistrationException;
}
