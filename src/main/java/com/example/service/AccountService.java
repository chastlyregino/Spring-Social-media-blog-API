package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Autowired //do I need to do it as a constructor injection?
    AccountRepository accountRepository;

    public Account addAccount(Account account) {
        Optional<Account> optionalAccount = accountRepository.findById(Long.valueOf(account.getUsername()));
        
        if (!account.getUsername().isEmpty()
            && account.getPassword().length() > 3
            && !optionalAccount.isPresent()) {
                return accountRepository.save(account);
        }
        
        return null;
    }

    public Account loginAccount(Account account) {
        Optional<Account> optionalAccount = accountRepository.findById(Long.valueOf(account.getUsername()));
        Account accountRetrived = null;

        if (optionalAccount.isPresent()){
            accountRetrived = optionalAccount.get();

            if (accountRetrived.getPassword().equalsIgnoreCase(account.getPassword())) {
                return accountRetrived; //returning accountId as = 0
            }
        }

        return null;
    }
}
