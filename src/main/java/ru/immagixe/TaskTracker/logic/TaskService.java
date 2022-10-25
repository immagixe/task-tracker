package ru.immagixe.TaskTracker.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.immagixe.TaskTracker.logic.model.Task;
import ru.immagixe.TaskTracker.logic.repositories.TaskRepository;
import ru.immagixe.TaskTracker.security.models.Account;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findByOwner(Account owner) {
        return taskRepository.findByOwner(owner);
    }
}
