package com.accenture.modulosPago.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TransactionDto {

    private Double amount;

    private String transactionDate;
    private String beneficiary;
    private String accountNumberOrigin; //sender
    private String accountNumberDestination;
    private String description;

    public TransactionDto() {
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getAccountNumberOrigin() {
        return accountNumberOrigin;
    }

    public void setAccountNumberOrigin(String accountNumberOrigin) {
        this.accountNumberOrigin = accountNumberOrigin;
    }

    public String getAccountNumberDestination() {
        return accountNumberDestination;
    }

    public void setAccountNumberDestination(String accountNumberDestination) {
        this.accountNumberDestination = accountNumberDestination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
