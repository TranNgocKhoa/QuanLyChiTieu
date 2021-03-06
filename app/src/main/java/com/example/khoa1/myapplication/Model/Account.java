package com.example.khoa1.myapplication.Model;

/**
 * Created by khoa1 on 10/31/2017.
 */

public class Account {
    private int id;
    private String name;
    private int balance;
    private int debit;
    private AccountType accountType;

    public Account(int id, String name, int balance, int debit, AccountType accountType) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.debit = debit;
        this.accountType = accountType;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
