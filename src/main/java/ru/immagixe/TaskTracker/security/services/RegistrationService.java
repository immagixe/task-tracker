package ru.immagixe.TaskTracker.security.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.immagixe.TaskTracker.security.models.User;
import ru.immagixe.TaskTracker.security.repositories.UserRepository;
import ru.immagixe.TaskTracker.security.util.EmailAlreadyExistsException;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    public void checkEmailExists(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }
    }
}
