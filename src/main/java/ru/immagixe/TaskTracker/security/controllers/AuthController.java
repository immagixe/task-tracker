package ru.immagixe.TaskTracker.security.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.immagixe.TaskTracker.logic.dto.AccountRegisterDTO;
import ru.immagixe.TaskTracker.logic.dto.TaskDTO;
import ru.immagixe.TaskTracker.logic.model.Task;
import ru.immagixe.TaskTracker.security.models.Account;
import ru.immagixe.TaskTracker.security.services.RegistrationService;
import ru.immagixe.TaskTracker.security.util.PersonValidator;

import javax.validation.Valid;

@Controller
@CrossOrigin("http://localhost:63342/")
//@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService, ModelMapper modelMapper) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/main")
    public String index() {
        return "main";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("account") Account account) {
        return "registration";
    }


//    @PostMapping(path = "/registration", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<HttpStatus> postData(@ModelAttribute AccountRegisterDTO dto) {


    @PostMapping(path = "/registration", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String performRegistration(@ModelAttribute("account") @Valid Account account,
                                      BindingResult bindingResult) {
        personValidator.validate(account, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }



        System.out.println(account.getEmail() + " " + account.getPassword());

        registrationService.register(account);

        return "redirect:/login";
    }

    private Account convertToAccount(AccountRegisterDTO accountRegisterDTO) {
        return modelMapper.map(accountRegisterDTO, Account.class);
    }
}
