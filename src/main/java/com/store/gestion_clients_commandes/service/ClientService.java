package com.store.gestion_clients_commandes.service;

import com.store.gestion_clients_commandes.model.Client;
import com.store.gestion_clients_commandes.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client ajouterClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> listerClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> recupererClientParId(Long id) {
        return clientRepository.findById(id);
    }
}