package yevhen.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import yevhen.bookstore.dto.UserLoginRequestDto;
import yevhen.bookstore.dto.UserLoginResponseDto;
import yevhen.bookstore.dto.UserRegistrationRequestDto;
import yevhen.bookstore.dto.UserResponseDto;
import yevhen.bookstore.exception.RegistrationException;
import yevhen.bookstore.security.AuthenticationService;
import yevhen.bookstore.service.UserService;

@Tag(name = "Authentication", description = "Endpoints for registration and authentication")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    @Operation(summary = "Registration", description = "Registration new user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.registration(requestDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Authentication",
            description = "Login for existing user by email and password")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
