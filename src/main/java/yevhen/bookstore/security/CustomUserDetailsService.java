package yevhen.bookstore.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import yevhen.bookstore.exception.EntityNotFoundException;
import yevhen.bookstore.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("Can't find user by email:"
                        + username));
    }
}
