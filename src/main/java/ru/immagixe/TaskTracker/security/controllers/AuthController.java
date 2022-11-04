package ru.immagixe.TaskTracker.security.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.immagixe.TaskTracker.logic.utils.MapperUtil;
import ru.immagixe.TaskTracker.logic.dto.UserDTO;
import ru.immagixe.TaskTracker.logic.dto.UserRegisterDTO;
import ru.immagixe.TaskTracker.security.models.User;
import ru.immagixe.TaskTracker.security.securityDetails.TaskTrackerUserDetails;
import ru.immagixe.TaskTracker.security.services.RegistrationService;
import ru.immagixe.TaskTracker.security.util.EmailAlreadyExistsException;
import ru.immagixe.TaskTracker.security.util.UserValidator;

import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:63342/")
//@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(UserValidator userValidator, RegistrationService registrationService) {
        this.userValidator = userValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/main")
    public String index() {
        return "main";
    }

//    @PostMapping(path = "/registration", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<HttpStatus> postData(@ModelAttribute AccountRegisterDTO dto) {

    @PostMapping(path = "/user", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HttpStatus> performRegistration(@ModelAttribute("account") @Valid User user) {
        try {
            registrationService.checkEmailExists(user);
        } catch (EmailAlreadyExistsException exc) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This email is already taken", exc);
        }
        registrationService.register(user);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
