package com.accenture.modulosPago.service;

import com.accenture.modulosPago.clients.UserClientRest;
import com.accenture.modulosPago.dtos.AccountDto;
import com.accenture.modulosPago.entities.Account;
import com.accenture.modulosPago.models.User;
import com.accenture.modulosPago.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("accountServiceFeign")
public class AccountServiceFeign implements InterfaceAccountService{

    @Autowired
    private UserClientRest userClientRestFeign;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createdAccount(User user){
        Account account;
        do {
            account = new Account(user);
        } while (accountRepository.findByAccountNumber(account.getAccountNumber()) != null
                || accountRepository.findByCbu(account.getCbu()) != null);
        return accountRepository.save(account);
    }


    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account LastCreatedAccount(Long idUser) {
        return accountRepository.findAllByIdUserOrderByIdDesc(idUser)
                .stream().findFirst()
                .orElse(null);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Account findByCbu(String cbu) {
        return accountRepository.findByCbu(cbu);
    }

    @Override
    public List<Account> findByIdUser(Long idUser) {
        return accountRepository.findByIdUser(idUser);
    }

    @Override
    public Boolean deleteByIdAccount(Long idAccount) {
        Optional<Account> acc = accountRepository.findById(idAccount);
        if(acc.isPresent() && Utils.verifyBalanceAccount(acc.get())){
            accountRepository.delete(acc.get());
            return true;
        }else{
            return false;
        }
    }
}


 /*
        List<Account> listAccount = new ArrayList<>();
        List<User> listUsers = userClientRestFeign.findAll().stream().collect(Collectors.toList());
        for (int i = 0; i < listUsers.size(); i++) {
            listAccount.addAll(accountRepository.findAllByIdUser(listUsers.get(i).getIdAccount()));
        }
        return listAccount;*/
