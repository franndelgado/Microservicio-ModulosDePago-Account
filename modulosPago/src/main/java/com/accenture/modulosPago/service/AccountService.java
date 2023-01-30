package com.accenture.modulosPago.service;

import com.accenture.modulosPago.dtos.AccountDto;
import com.accenture.modulosPago.entities.Account;
import com.accenture.modulosPago.models.User;
import com.accenture.modulosPago.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import utils.Utils;

import java.util.List;
import java.util.Optional;

@Service("accountServiceRest")
@Primary
public class AccountService implements InterfaceAccountService{

    @Autowired
    private RestTemplate clientRest;
    @Autowired
    private AccountRepository accountRepository;

    public Account createdAccount(User user){
        Account accountNew;
        do {
            accountNew = new Account(user);
            System.out.println(accountNew.getId());
        } while (accountRepository.findByAccountNumber(accountNew.getAccountNumber()) != null
                || accountRepository.findByCbu(accountNew.getCbu()) != null);
        accountRepository.save(accountNew);

        clientRest.postForEntity("http://localhost:8001/api/users/addAccountUser/",accountNew,Account.class);

        return accountNew;
    }

    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return (List<Account>) accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Account LastCreatedAccount(Long idUser){
        return accountRepository.findAllByIdUserOrderByIdDesc(idUser)
                .stream().findFirst()
                .orElse(null);
    }

    @Override
    public Account findByAccountNumber(String accountNumber){
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
