package com.store.gestion_clients_commandes.controller;

<<<<<<< HEAD
import com.store.gestion_clients_commandes.exception.ResourceNotFoundException;
=======
import com.store.gestion_clients_commandes.dto.ClientRequest;
>>>>>>> main
import com.store.gestion_clients_commandes.model.Client;
import com.store.gestion_clients_commandes.service.ClientService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client ajouterClient(@Valid @RequestBody ClientRequest clientRequest) {
        Client client = new Client();
        client.setNom(clientRequest.getNom());
        client.setEmail(clientRequest.getEmail());
    	
    	return clientService.ajouterClient(client);
    }

    @GetMapping
    public List<Client> listerClients() {
        return clientService.listerClients();
    }

    @GetMapping("/{id}")
    public Client recupererClientParId(@PathVariable Long id) {
        return clientService.recupererClientParId(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'id : " + id));
    }
}