package com.example.lab2;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BankServer {
    Map<String, BankAccount> accounts = new HashMap<>();

    @PostMapping("/openBankAccount")
    String newBanckAccount(@RequestBody String name) {
        if (accounts.containsKey(name)) {
            return "Account already exists";
        }

        BankAccount newAcc = new BankAccount(name);
        accounts.put(name, newAcc);
        return "Successfully created new account: " + newAcc.nameHolder;
    }

    @GetMapping("/getAccount")
    String getAccountDesc(@RequestParam(value = "name") String name) {
        BankAccount acc = accounts.get(name);

        if (acc == null) return "No account with such name";

        return acc.toString();
    }

    @PostMapping("/depositMoney")
    String depositMoney(@RequestParam(value = "name") String name, @RequestBody double amount) {
        BankAccount acc = accounts.get(name);
        if (acc == null) return "No account with such name";

        acc.deposit(amount);
        return "Successfully added balance to account: " + acc;
    }

    @PostMapping("/withdrawMoney")
    String withdrawMoney(@RequestParam(value = "name") String name, @RequestBody double amount) {
        BankAccount acc = accounts.get(name);

        if (acc == null) return "No account with such name";

        try {
            acc.withdraw(amount);
            return "Successfully withdrew balance from account: " + acc;
        } catch (Exception e) {
            return "Not enough money on the account: " + acc;
        }
    }
}
