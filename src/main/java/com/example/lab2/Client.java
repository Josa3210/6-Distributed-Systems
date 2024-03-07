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
        this.restClient = RestClient.builder().baseUrl("http://localhost:8080").build();
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
        ResponseEntity<String> response = this.restClient.post().uri(this.URIBase + "/openBankAccount").body(this.user.getName()).retrieve().toEntity(String.class);
        System.out.println(response.getBody());
    }

    public void getAccount() {
        ResponseEntity<String> response = this.restClient.get().uri(this.URIBase + "/getAccount?name={name}", this.user.getName()).retrieve().toEntity(String.class);
        System.out.println(response.getBody());
    }

    public void depositMoney(double amount) {
        ResponseEntity<String> response = this.restClient.post().uri(this.URIBase + "/depositMoney?name={name}", this.user.getName()).contentType(MediaType.APPLICATION_JSON).body(amount).retrieve().toEntity(String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            this.user.setMoney(this.user.getMoney() - amount);
        }
        System.out.println(response.getBody());
        System.out.println("Users money " + this.user.getMoney());
    }

    public void withdrawMoney(double amount) {
        ResponseEntity<String> response = this.restClient.post().uri(this.URIBase + "/withdrawMoney?name={name}", this.user.getName()).contentType(MediaType.APPLICATION_JSON).body(amount).retrieve().toEntity(String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            this.user.setMoney(this.user.getMoney() + amount);
        }
        System.out.println(response.getBody());
        System.out.println("Users money " + this.user.getMoney());
    }

    public void deleteBankAccount() {
        ResponseEntity<String> response = this.restClient.post().uri(this.URIBase + "/deleteBankAccount").contentType(MediaType.APPLICATION_JSON).body(this.user.getName()).retrieve().toEntity(String.class);
        System.out.println(response.getBody());
    }

}
