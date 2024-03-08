package com.example.lab2;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BankServer {
    // The "database"
    Map<String, BankAccount> accounts = new HashMap<>();

    // Create a new BankAccount based on the name that is sent
    @PostMapping("/openBankAccount")
    String newBanckAccount(@RequestBody String name) {

        // Check if the account already exists in the database
        if (accounts.containsKey(name)) {
            return "Account already exists";
        }

        // Create a new account and put it in the database
        BankAccount newAcc = new BankAccount(name);
        accounts.put(name, newAcc);
        return "Successfully created new account: " + newAcc.nameHolder;
    }

    // Get the values of the BankAccount that belong to a certain name
    @GetMapping("/getAccount")
    String getAccountDesc(@RequestParam(value = "name") String name) {
        // Try to get the account with the associated name
        BankAccount acc = accounts.get(name);
        // If not in the database, return nothing
        if (acc == null) return null;

        // Return the account as a string
        return acc.toString();
    }

    // Transfer money from the User to the BankAccount
    @PostMapping("/depositMoney")
    String depositMoney(@RequestParam(value = "name") String name, @RequestBody double amount) {

        // Check if the account exists
        BankAccount acc = accounts.get(name);
        if (acc == null) return "No account with such name";

        // Deposit the money
        acc.deposit(amount);
        return "Successfully added balance to account: " + acc;
    }

    // Transfer money from the BankAccount to the user
    @PostMapping("/withdrawMoney")
    String withdrawMoney(@RequestParam(value = "name") String name, @RequestBody double amount) {

        // Check if the account exists
        BankAccount acc = accounts.get(name);
        if (acc == null) return "No account with such name";

        // Try withdrawing the money
        try {
            acc.withdraw(amount);
            return "Successfully withdrew balance from account: " + acc;
        } catch (Exception e) {
            return "Not enough money on the account: " + acc;
        }
    }

    // Delete the BankAccount from the "database"
    @PostMapping("/deleteBankAccount")
    String deleteAccount(@RequestBody String name) {

        // Check if the account exists
        BankAccount acc = accounts.get(name);
        if (acc == null) return "No account with such name";

        // Remove the account from the database
        accounts.remove(name);
        return "Successfully removed: " + name;
    }
}
