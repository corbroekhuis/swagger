package com.rabo.yepp.model;

import java.time.LocalDateTime;

public class Transaction {

    long id;
    LocalDateTime txTime;
    String accountFrom;
    String accountTo;
    double amount;
    String description;

    public Transaction() {
    }

    public Transaction(long id, LocalDateTime txTime, String accountFrom, String accountTo, double amount, String description) {
        this.id = id;
        this.txTime = txTime;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTxTime() {
        return txTime;
    }

    public void setTxTime(LocalDateTime txTime) {
        this.txTime = txTime;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", txTime=" + txTime +
                ", accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
