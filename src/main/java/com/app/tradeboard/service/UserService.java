package com.app.tradeboard.service;

import com.app.tradeboard.model.User;
import com.app.tradeboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }
}
