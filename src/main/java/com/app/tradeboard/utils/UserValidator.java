package com.app.tradeboard.utils;

import com.app.tradeboard.model.User;
import com.app.tradeboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (userService.findUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("login", "",
                    "Пользователь с такой почтой уже зарегистрирован");
        }
    }
}
