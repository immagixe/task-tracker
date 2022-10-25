package ru.immagixe.TaskTracker.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.immagixe.TaskTracker.security.models.Account;
import ru.immagixe.TaskTracker.security.repositories.AccountRepository;
import ru.immagixe.TaskTracker.security.securityDetails.AccountDetails;

import java.util.Optional;

@Service
public class AccountDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountDetailService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> person = accountRepository.findByEmail(username);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new AccountDetails(person.get());
    }
}
