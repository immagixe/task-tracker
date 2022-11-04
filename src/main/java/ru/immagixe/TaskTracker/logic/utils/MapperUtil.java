package ru.immagixe.TaskTracker.logic.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.immagixe.TaskTracker.logic.dto.TaskDTO;
import ru.immagixe.TaskTracker.logic.dto.UserDTO;
import ru.immagixe.TaskTracker.logic.model.Task;
import ru.immagixe.TaskTracker.security.models.User;

@Component
public class MapperUtil {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public TaskDTO convertToTaskDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    public Task convertToTask(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }
}
