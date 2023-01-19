package com.accenture.modulosPago.service;

import com.accenture.modulosPago.models.Account;
import com.accenture.modulosPago.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return (List<Account>) accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }
}
