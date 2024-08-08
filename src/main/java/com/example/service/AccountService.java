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
        return accountRepository.save(account);
    }

    // public Account loginAccount(Account account) {
    //     Account userCredentials = this.accountDAO.getAccountByUsername(account.getUsername());
    //     if ((userCredentials != null)
    //     && userCredentials.getPassword().equalsIgnoreCase(account.getPassword())) {
    //         return userCredentials;
    //     }
    //     return null;
    // }
    // ^ How can I retrieve the account name if I don't have the id?
}
