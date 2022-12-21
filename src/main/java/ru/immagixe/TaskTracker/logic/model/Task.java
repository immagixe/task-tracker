package ru.immagixe.TaskTracker.logic.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.immagixe.TaskTracker.security.models.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "header")
    private String header;

    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "status_active")
    private boolean statusActive;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", statusActive=" + statusActive +
                ", " +
                '}';
    }
}
