package com.example.lab2;

public class BankAccount {
    String nameHolder;
    Double balance;

    public BankAccount(String nameHolder) {
        this.nameHolder = nameHolder;
        this.balance = 0.;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        if (amount < this.balance) {
            this.balance -= amount;
        } else {
            throw new Exception("Not enough money");
        }
    }

    public String showBalance() {
        return String.valueOf(this.balance);
    }

    @Override
    public String toString() {
        return String.format("BankAccount(holder: %s, balance: %s)",
                this.nameHolder, this.balance);
    }
}
