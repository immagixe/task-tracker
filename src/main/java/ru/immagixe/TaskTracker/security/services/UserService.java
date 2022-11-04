package ru.immagixe.TaskTracker.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.server.ResponseStatusException;
import ru.immagixe.TaskTracker.security.models.User;
import ru.immagixe.TaskTracker.security.repositories.UserRepository;
import ru.immagixe.TaskTracker.security.securityDetails.TaskTrackerUserDetails;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        Optional<User> user = userRepository.findByEmail(username);
        return user;
    }

    public Optional<User> findById(int userId) {
        Optional<User> user = userRepository.findById(userId);
        return user;
    }

    public User findByUserDetails (TaskTrackerUserDetails taskTrackerUserDetails) {
        int userId = taskTrackerUserDetails.getUser().getId();
        return findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist"));
    }
}
