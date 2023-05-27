package com.app.tradeboard.service;

import com.app.tradeboard.model.User;
import com.app.tradeboard.repository.UserRepository;
import com.app.tradeboard.utils.Enums.UserRole;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.ROLE_USER);
        userRepository.save(user);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode((password));
    }
}
