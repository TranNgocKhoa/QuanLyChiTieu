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
    private byte[] picture;

    public Account(int id, String name, int balance, int debit,byte[] picture, AccountType accountType) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.debit = debit;
        this.accountType = accountType;
        this.picture=picture;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
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
