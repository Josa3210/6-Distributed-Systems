package com.example.lab2;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BankServer {
    Map<String,BankAccount> accounts = new HashMap<>();

    @PostMapping("/openBankAccount")
    String newBanckAccount(@RequestBody String name) {
        BankAccount newAcc = new BankAccount(name);
        accounts.put(name,newAcc);
        return "Successfully created new account: " + newAcc.nameHolder;
    }

    @GetMapping("/getAccount")
    String getAccount(@RequestParam(value = "name") String name) {
        return accounts.get(name).toString();
    }

    @PostMapping("/depositMoney")
    String depositMoney(@RequestParam(value="name") String name, @RequestBody double amount){
        BankAccount acc = accounts.get(name);
        acc.deposit(amount);
        return "Successfully added balance to account: " + acc;
    }
}
