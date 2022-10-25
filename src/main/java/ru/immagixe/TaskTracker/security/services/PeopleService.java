package ru.immagixe.TaskTracker.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.immagixe.TaskTracker.security.models.Account;
import ru.immagixe.TaskTracker.security.repositories.AccountRepository;

import java.util.Optional;

@Service
public class PeopleService {

    private final AccountRepository accountRepository;

    @Autowired
    public PeopleService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Optional<Account> findByUsername(String username) {
        Optional<Account> person = accountRepository.findByEmail(username);
        return person;
    }


}
