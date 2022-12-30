package ru.immagixe.TaskTracker.emailgreeting.interfaces;

import ru.immagixe.TaskTracker.security.model.User;

public interface EmailCreator {

    String createEmailAddress(User user);

    String createEmailTitle(User user);

    String createEmailText(User user);
}
