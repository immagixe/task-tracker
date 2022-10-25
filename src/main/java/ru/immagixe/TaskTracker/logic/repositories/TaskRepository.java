package ru.immagixe.TaskTracker.logic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.immagixe.TaskTracker.logic.model.Task;
import ru.immagixe.TaskTracker.security.models.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByOwner(Account owner);
}
