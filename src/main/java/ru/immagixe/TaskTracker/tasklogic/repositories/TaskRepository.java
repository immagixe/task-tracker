package ru.immagixe.TaskTracker.tasklogic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.immagixe.TaskTracker.tasklogic.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByOwnerId(int ownerId);
}
