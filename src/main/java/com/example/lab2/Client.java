package com.example.lab2;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

public class Client {
    String name;
    String URIBase;
    RestClient restClient;

    public Client(String name) {
        this.name = name;
        this.restClient = RestClient.builder().baseUrl("http://localhost:8080").build();
        this.URIBase = "http://localhost:8080";
    }

    public static void main(String[] args) {
        Client client = new Client("Joeri");
        client.getAccount();
        client.createBankAccount();
        client.getAccount();
        client.depositMoney(100);
        client.depositMoney(20);
        client.withdrawMoney(100);
        client.withdrawMoney(50);
        client.deleteBankAccount();
    }

    public void createBankAccount() {
        ResponseEntity<String> response = this.restClient.post().uri(this.URIBase + "/openBankAccount").contentType(MediaType.APPLICATION_JSON).body(name).retrieve().toEntity(String.class);
        System.out.println(response.getBody());
        // if (result.getStatusCode() == org.springframework.http.HttpStatusCode.valueOf(200)){
        //     System.out.println("Successfully created account");
        // }
    }

    public void getAccount() {
        ResponseEntity<String> response = this.restClient.get().uri(this.URIBase + "/getAccount?name={name}", name).retrieve().toEntity(String.class);
        System.out.println(response.getBody());
    }

    public void depositMoney(double amount) {
        ResponseEntity<String> response = this.restClient.post().uri(this.URIBase + "/depositMoney?name={name}", name).contentType(MediaType.APPLICATION_JSON).body(amount).retrieve().toEntity(String.class);
        System.out.println(response.getBody());
    }

    public void withdrawMoney(double amount) {
        ResponseEntity<String> response = this.restClient.post().uri(this.URIBase + "/withdrawMoney?name={name}", name).contentType(MediaType.APPLICATION_JSON).body(amount).retrieve().toEntity(String.class);
        System.out.println(response.getBody());
    }

    public void deleteBankAccount() {
        ResponseEntity<String> response = this.restClient.post().uri(this.URIBase + "/deleteBankAccount").contentType(MediaType.APPLICATION_JSON).body(name).retrieve().toEntity(String.class);
        System.out.println(response.getBody());
    }

}
