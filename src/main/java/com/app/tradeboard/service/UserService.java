package com.app.tradeboard.service;

import com.app.tradeboard.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.app.tradeboard.model.User;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }
}
