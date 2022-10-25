package ru.immagixe.TaskTracker.logic.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.immagixe.TaskTracker.logic.TaskService;
import ru.immagixe.TaskTracker.logic.dto.AccountRegisterDTO;
import ru.immagixe.TaskTracker.logic.dto.TaskDTO;
import ru.immagixe.TaskTracker.logic.dto.UserDTO;
import ru.immagixe.TaskTracker.logic.model.Task;
import ru.immagixe.TaskTracker.logic.repositories.TaskRepository;
import ru.immagixe.TaskTracker.security.models.Account;
import ru.immagixe.TaskTracker.security.securityDetails.AccountDetails;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin("http://localhost:63342/")
@RestController
public class TaskController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/user")
    public String createUser() {
        return "heelo!";
    }

    @GetMapping("/user")
    public UserDTO getUser(@AuthenticationPrincipal AccountDetails accountDetails) {
        if (accountDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return convertToAccountDTO(accountDetails.getAccount());
    }

    @GetMapping("/tasks")
    public List<TaskDTO> getTasks(@AuthenticationPrincipal AccountDetails accountDetails) {
        if (accountDetails == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return taskService.findByOwner(accountDetails.getAccount()).stream()
                .map(this::convertToTaskDTO)
                .collect(Collectors.toList());
    }

//    @PostMapping("/session")


    @PostMapping(path = "/postdata", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HttpStatus> postData(@ModelAttribute AccountRegisterDTO dto) {


        System.out.println(dto.getEmail() + " ");


        return ResponseEntity.ok(HttpStatus.OK);
    }

    private UserDTO convertToAccountDTO(Account account) {
        return modelMapper.map(account, UserDTO.class);
    }

    private TaskDTO convertToTaskDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }
}
