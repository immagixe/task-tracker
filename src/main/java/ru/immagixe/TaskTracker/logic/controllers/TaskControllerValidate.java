package ru.immagixe.TaskTracker.logic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.immagixe.TaskTracker.logic.TaskService;
import ru.immagixe.TaskTracker.logic.dto.TaskDTO;
import ru.immagixe.TaskTracker.logic.model.Task;
import ru.immagixe.TaskTracker.security.models.User;
import ru.immagixe.TaskTracker.security.securityDetails.TaskTrackerUserDetails;
import ru.immagixe.TaskTracker.security.services.UserService;

public class TaskControllerValidate {

    protected final TaskService taskService;
    protected final UserService userService;

    @Autowired
    public TaskControllerValidate(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    protected void validateAccess(TaskDTO taskDTO, User user) {
        Task foundedTask = taskService.findById(taskDTO.getId());

        if (foundedTask.getOwner().getId() != user.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
    }

    protected void checkAuthorization(TaskTrackerUserDetails taskTrackerUserDetails) {
        if (taskTrackerUserDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}

