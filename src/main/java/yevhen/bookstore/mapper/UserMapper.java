package yevhen.bookstore.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import yevhen.bookstore.dto.user.UserRegistrationRequestDto;
import yevhen.bookstore.dto.user.UserResponseDto;
import yevhen.bookstore.model.User;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl"
)
public interface UserMapper {
    UserResponseDto toUserResponseDto(User user);

    User toUserModel(UserRegistrationRequestDto requestDto);
}
