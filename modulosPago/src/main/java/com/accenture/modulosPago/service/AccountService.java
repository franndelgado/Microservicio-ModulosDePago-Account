package com.accenture.modulosPago.service;

import com.accenture.modulosPago.dtos.AccountDto;
import com.accenture.modulosPago.dtos.TransactionDto;
import com.accenture.modulosPago.entities.Account;
import com.accenture.modulosPago.models.User;
import com.accenture.modulosPago.repositories.AccountRepository;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import utils.Utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        return accountNew;
    }

    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return (List<Account>) accountRepository.findAll();
    }


    public List<Account> findAccounts(Long idUser) {
        System.out.println(accountRepository.findByIdUserAndStatus(idUser, true));
        return accountRepository.findByIdUserAndStatus(idUser, true);
    }

    @Override
    public Long findLastUserWithAccount(Long idUser) {
        Map<String,String> pathVariables = new HashMap<String, String>();
        pathVariables.put("idUser", idUser.toString());
        User userAux = clientRest.getForObject("http://localhost:8001/api/users/detail/{idUser}", User.class, pathVariables);
        if(userAux != null){
            return userAux.getId();
        }else{
            return 0L;
        }
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
            clientRest.postForEntity("http://localhost:8001/api/users/deleteAccountList/", acc, Account.class);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean updatedBalance(TransactionDto transactionDto){
        try {
            Account accountOrigin = accountRepository.findByAccountNumber(transactionDto.getAccountNumberOrigin());
            Account accountDestination = accountRepository.findByAccountNumber(transactionDto.getAccountNumberDestination());
            accountOrigin.setBalance(accountOrigin.getBalance().subtract(BigDecimal.valueOf(transactionDto.getAmount())));
            accountDestination.setBalance(accountDestination.getBalance().add(BigDecimal.valueOf(transactionDto.getAmount())));
            accountRepository.save(accountOrigin);
            accountRepository.save(accountDestination);
            return true;
        }
        catch (Exception ex){
            ex.getMessage();
            return false;
        }
    }
}
//clientRest.postForEntity("http://localhost:8002/api/user/deleteAccountToListUser/",acc,Account.class);