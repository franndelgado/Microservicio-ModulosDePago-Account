package com.accenture.modulosPago.dtos;

import com.accenture.modulosPago.entities.Account;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDto {
    private String cbu;
    private BigDecimal balance;
    private String accountNumber;
    private LocalDate creationDate;

    public AccountDto(Account account) {
        this.cbu = account.getCbu();
        this.balance = account.getBalance();
        this.accountNumber = account.getAccountNumber();
        this.creationDate = account.getCreationDate();
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
}
