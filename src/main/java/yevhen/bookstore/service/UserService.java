package yevhen.bookstore.service;

import yevhen.bookstore.dto.UserRegistrationRequestDto;
import yevhen.bookstore.dto.UserResponseDto;
import yevhen.bookstore.exception.RegistrationException;

public interface UserService {
    UserResponseDto registration(UserRegistrationRequestDto requestDto)
            throws RegistrationException;
}
