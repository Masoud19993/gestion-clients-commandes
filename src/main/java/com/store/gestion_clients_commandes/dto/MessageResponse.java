package com.store.gestion_clients_commandes.dto;

public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}