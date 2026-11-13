package com.apiece.springboot_sns_sample.domain.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw UserException.duplicateUsername(user.getUsername());
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
