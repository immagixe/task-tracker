package ru.immagixe.TaskTracker.security.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmailErrorResponse {
    private String message;
    private long timestamp;
}
