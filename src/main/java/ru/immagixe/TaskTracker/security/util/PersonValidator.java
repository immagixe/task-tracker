package ru.immagixe.TaskTracker.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.immagixe.TaskTracker.security.models.Account;
import ru.immagixe.TaskTracker.security.services.PeopleService;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;

        if (peopleService.findByUsername(account.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "Email with the same name already exists");
        }
    }
}
