package com.apiece.springboot_sns_sample.domain.user;

import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    // BCrypt only uses the first 72 bytes of input and Spring Security rejects anything longer
    private static final int MAX_PASSWORD_BYTES = 72;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw UserException.duplicateUsername(user.getUsername());
        }
        if (user.getPassword().getBytes(StandardCharsets.UTF_8).length > MAX_PASSWORD_BYTES) {
            throw UserException.passwordTooLong(MAX_PASSWORD_BYTES);
        }
        user.encodePassword(passwordEncoder.encode(user.getPassword()));
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Concurrent signup with the same username hit the unique constraint
            throw UserException.duplicateUsername(user.getUsername());
        }
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> UserException.notFound(id));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> UserException.notFound(username));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
