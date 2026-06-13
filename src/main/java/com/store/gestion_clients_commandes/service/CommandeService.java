package com.store.gestion_clients_commandes.service;

import com.store.gestion_clients_commandes.exception.ResourceNotFoundException;
import com.store.gestion_clients_commandes.model.Client;
import com.store.gestion_clients_commandes.model.Commande;
import com.store.gestion_clients_commandes.repository.ClientRepository;
import com.store.gestion_clients_commandes.repository.CommandeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;

    public CommandeService(CommandeRepository commandeRepository, ClientRepository clientRepository) {
        this.commandeRepository = commandeRepository;
        this.clientRepository = clientRepository;
    }

    public Commande ajouterCommande(Long clientId, Commande commande) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'id : " + clientId));

        commande.setClient(client);

        return commandeRepository.save(commande);
    }

    public List<Commande> listerCommandes() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> recupererCommandeParId(Long id) {
        return commandeRepository.findById(id);
    }
    
    public void supprimerCommande(Long id) {
        if (!commandeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Commande non trouvée avec l'id : " + id);
        }

        commandeRepository.deleteById(id);
    }
}