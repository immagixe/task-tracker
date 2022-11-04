package ru.immagixe.TaskTracker.logic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {
    private String email;
    private String password;
}
