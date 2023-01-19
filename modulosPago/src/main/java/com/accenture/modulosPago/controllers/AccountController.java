package com.accenture.modulosPago.controllers;

import com.accenture.modulosPago.models.Account;
import com.accenture.modulosPago.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/list")
    public List<Account> getListAccounts(){
        return accountService.findAll();
    }
    @GetMapping("/detail/{id}")
    public Account detail(@PathVariable Long id){
        return accountService.findById(id);
    }

}
