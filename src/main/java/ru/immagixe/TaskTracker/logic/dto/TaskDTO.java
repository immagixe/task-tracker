package ru.immagixe.TaskTracker.logic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
    private int id;
    private String header;
    private String description;
    private boolean statusActive;
}
