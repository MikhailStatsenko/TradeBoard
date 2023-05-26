package com.app.tradeboard.service;

import com.app.tradeboard.repository.UserRepository;
import com.app.tradeboard.utils.Enums.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.app.tradeboard.model.User;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.ROLE_ADMIN);
        userRepository.save(user);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode((password));
    }
}
