package ru.immagixe.TaskTracker.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.immagixe.TaskTracker.security.models.User;
import ru.immagixe.TaskTracker.security.services.UserService;

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

        if (userService.findByUsername(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
//            errors.rejectValue("email", "", "Email with the same name already exists");
        }
    }
}
