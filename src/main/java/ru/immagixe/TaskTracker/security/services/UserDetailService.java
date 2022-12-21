package ru.immagixe.TaskTracker.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.immagixe.TaskTracker.security.models.User;
import ru.immagixe.TaskTracker.security.repositories.UserRepository;
import ru.immagixe.TaskTracker.security.securityDetails.TaskTrackerUserDetails;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new TaskTrackerUserDetails(user.get());
    }
}
