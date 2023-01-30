package com.accenture.modulosPago.service;

import com.accenture.modulosPago.entities.Account;
import com.accenture.modulosPago.models.User;


import java.util.List;

public interface InterfaceAccountService {

    public Account createdAccount(User user);

    public List<Account> findAll();

    public Account findById(Long id);

    public Account LastCreatedAccount(Long idUser);

    public  Account findByAccountNumber(String accountNumber);

    public  Account findByCbu(String cbu);

    public  List<Account> findByIdUser(Long idUser);

    public Boolean deleteByIdAccount(Long idAccount);

}
