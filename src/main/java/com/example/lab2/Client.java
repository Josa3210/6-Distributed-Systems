package com.example.lab2;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

public class Client {
    User user;
    String URIBase;
    RestClient restClient;

    public Client(User user) {
        this.user = user;
        this.restClient = RestClient.create();
        this.URIBase = "http://localhost:8080";
    }

    public static void main(String[] args) {
        User user = new User("User");
        user.setMoney(200);

        Client client1 = new Client(user);
        Client client2 = new Client(user);

        client1.createBankAccount();
        client1.getAccount();
        client1.depositMoney(100);

        client2.depositMoney(20);

        client1.withdrawMoney(100);

        client2.withdrawMoney(50);
        client2.deleteBankAccount();
    }

    public void createBankAccount() {
        // Send a request and save the response in a
        // ResponseEntity of which the body is a string
        ResponseEntity<String> response =
                this.restClient.post()                           // Send POST-request
                .uri(this.URIBase + "/openBankAccount")      // Send to URL: http://localhost:8080/openBankAccount
                .body(this.user.getName())                       // Send the name of the user in the RequestBody
                .retrieve()                                      // Retrieve any response
                .toEntity(String.class);                         // Convert the response to a String

        // Print out the response body
        System.out.println(response.getBody());
    }

    public void getAccount() {
        // Send GET-request
        ResponseEntity<String> response =
                this.restClient.get()
                .uri(this.URIBase + "/getAccount?name={name}", this.user.getName())
                .retrieve()
                .toEntity(String.class);

        // Print the response
        System.out.println(response.getBody());
    }

    public void depositMoney(double amount) {
        // Check if the user has enough money to transfer
        if (this.user.getMoney() < amount) {
            System.out.println("Not enough money");
            return;
        }

        // Send POST-request
        ResponseEntity<String> response = this.restClient.post()
                .uri(this.URIBase + "/depositMoney?name={name}", this.user.getName())
                .contentType(MediaType.APPLICATION_JSON)
                .body(amount)
                .retrieve()
                .toEntity(String.class);

        // If successfully executed, subtract transferred money from user
        if (response.getStatusCode().is2xxSuccessful()) {
            this.user.setMoney(this.user.getMoney() - amount);
        }

        // Print the response and the remaining money of the user
        System.out.println(response.getBody());
        System.out.println("Users money " + this.user.getMoney());
    }

    public void withdrawMoney(double amount) {
        // Send post request
        ResponseEntity<String> response = this.restClient.post()
                .uri(this.URIBase + "/withdrawMoney?name={name}", this.user.getName())
                .contentType(MediaType.APPLICATION_JSON)
                .body(amount)
                .retrieve()
                .toEntity(String.class);

        // If successfully executed, add transferred money from user
        if (response.getStatusCode().is2xxSuccessful()) {
            this.user.setMoney(this.user.getMoney() + amount);
        }

        // Print the response and the remaining money of the user
        System.out.println(response.getBody());
        System.out.println("Users money " + this.user.getMoney());
    }

    public void deleteBankAccount() {
        // Send post request
        ResponseEntity<String> response = this.restClient.post()
                .uri(this.URIBase + "/deleteBankAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.user.getName())
                .retrieve()
                .toEntity(String.class);

        // Print the response
        System.out.println(response.getBody());
    }

}
