package ru.immagixe.TaskTracker.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.immagixe.TaskTracker.logic.model.Task;
import ru.immagixe.TaskTracker.logic.repositories.TaskRepository;
import ru.immagixe.TaskTracker.security.models.User;
import ru.immagixe.TaskTracker.security.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task findById(int userId) {
        Optional<Task> foundTask = taskRepository.findById(userId);
        return foundTask.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task does not exist"));
    }

    public List<Task> findByOwnerId(int ownerId) {
        return taskRepository.findByOwnerId(ownerId);
    }

    @Transactional
    public void save(Task task, User user) {
        task.setOwner(user);
        taskRepository.save(task);
    }

    @Transactional
    public void update(Task updatedTask, User user) {
        updatedTask.setOwner(user);
        taskRepository.save(updatedTask);
    }

    @Transactional
    public void delete(int taskId) {
        taskRepository.deleteById(taskId);
    }

    private List<Task> getUserTasks(int userId) {
        Optional<User> userFromDataBase = userRepository.findById(userId);
        return userFromDataBase.isPresent() ? userFromDataBase.get().getTasks() : Collections.emptyList();
    }
}
