package com.store.gestion_clients_commandes.controller;

import com.store.gestion_clients_commandes.model.Client;
import com.store.gestion_clients_commandes.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client ajouterClient(@RequestBody Client client) {
        return clientService.ajouterClient(client);
    }

    @GetMapping
    public List<Client> listerClients() {
        return clientService.listerClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> recupererClientParId(@PathVariable Long id) {
        return clientService.recupererClientParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}