package yevhen.bookstore.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import yevhen.bookstore.dto.UserRegistrationRequestDto;
import yevhen.bookstore.dto.UserResponseDto;
import yevhen.bookstore.exception.RegistrationException;
import yevhen.bookstore.mapper.UserMapper;
import yevhen.bookstore.model.Role;
import yevhen.bookstore.model.User;
import yevhen.bookstore.repository.RoleRepository;
import yevhen.bookstore.repository.UserRepository;
import yevhen.bookstore.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registration(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.email())) {
            throw new RegistrationException("User is already exists");
        }
        User user = userMapper.toUserModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        user.setRoles(Set.of(roleRepository.findRoleByName(Role.RoleName.ROLE_USER)));
        return userMapper.toUserResponseDto(userRepository.save(user));
    }
}
