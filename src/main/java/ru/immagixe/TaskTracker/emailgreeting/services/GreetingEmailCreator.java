package ru.immagixe.TaskTracker.emailgreeting.services;

import org.springframework.stereotype.Service;
import ru.immagixe.TaskTracker.emailgreeting.interfaces.EmailCreator;
import ru.immagixe.TaskTracker.security.model.User;

@Service
public class GreetingEmailCreator implements EmailCreator {

    @Override
    public String createEmailAddress(User user) {
        return user.getEmail();
    }

    @Override
    public String createEmailTitle(User user) {
        return "Спасибо за регистрацию в сервисе <TaskTracker>!";
    }

    @Override
    public String createEmailText(User user) {
        return "Добро пожаловать в наш сервис по отслеживанию задач! Желаем приятного использования!\n" +
                "Ваш логин для авторизации: " + user.getEmail();
    }
}
