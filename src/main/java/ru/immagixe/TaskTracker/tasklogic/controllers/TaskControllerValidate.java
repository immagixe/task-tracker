package ru.immagixe.TaskTracker.tasklogic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.immagixe.TaskTracker.tasklogic.TaskService;
import ru.immagixe.TaskTracker.tasklogic.model.dto.TaskDTO;
import ru.immagixe.TaskTracker.tasklogic.model.Task;
import ru.immagixe.TaskTracker.security.model.User;
import ru.immagixe.TaskTracker.security.securityDetails.TaskTrackerUserDetails;
import ru.immagixe.TaskTracker.security.services.UserService;

@RequiredArgsConstructor
public class TaskControllerValidate {

    protected final TaskService taskService;
    protected final UserService userService;

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

