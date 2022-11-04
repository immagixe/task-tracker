package ru.immagixe.TaskTracker.security.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.immagixe.TaskTracker.logic.model.Task;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
//@NamedEntityGraph(name = "account_entity_graph", attributeNodes = @NamedAttributeNode("tasks"))
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Email must not be empty")
//    @Size(min=2, max=100, message ="Username length must be between 2 and 100 characters")
    @Email
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
//    fetch = FetchType.EAGER,
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Task> tasks;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
