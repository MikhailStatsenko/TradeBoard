package com.app.tradeboard.service;

import com.app.tradeboard.model.Product;
import com.app.tradeboard.model.User;
import com.app.tradeboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
