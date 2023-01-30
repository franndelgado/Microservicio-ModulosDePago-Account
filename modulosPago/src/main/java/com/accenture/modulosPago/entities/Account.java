package com.accenture.modulosPago.entities;

import utils.Utils;
import com.accenture.modulosPago.dtos.AccountDto;
import com.accenture.modulosPago.models.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name ="native",strategy = "native")
    private Long id;

    @Column(unique = true)
    private String cbu;

    private BigDecimal balance;
    @Column(unique = true)
    private String accountNumber;
    private LocalDate creationDate;
    private Long idUser;

    @Transient
    private User user;
    public Account() {
    }

    public Account(String cbu, BigDecimal balance, String accountNumber, LocalDate creationDate ){
        this.cbu = cbu;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.creationDate = creationDate;
    }

    public Account(String cbu, BigDecimal balance, String accountNumber, LocalDate creationDate, User user) {
        this.cbu = cbu;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.creationDate = creationDate;
        this.user = user;
    }

    public Account(AccountDto accountDto, User user){
        this.cbu = accountDto.getCbu();
        this.balance = accountDto.getBalance();
        this.accountNumber = accountDto.getAccountNumber();
        this.creationDate = accountDto.getCreationDate();
        this.user = user;
    }



    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Account(User user) {
        this.cbu = Utils.generateRandomDigit(22);
        this.balance = new BigDecimal(0.00);
        this.accountNumber = Utils.generateRandomDigit(10);
        this.creationDate = LocalDate.now();
        this.user = user;
        this.idUser = user.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
