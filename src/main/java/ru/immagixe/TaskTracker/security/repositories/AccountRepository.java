package ru.immagixe.TaskTracker.security.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.immagixe.TaskTracker.security.models.Account;


import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

//    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "tasks")
    Optional<Account> findByEmail(String email);
}
