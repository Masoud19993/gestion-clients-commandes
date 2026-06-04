package com.store.gestion_clients_commandes.controller;

import com.store.gestion_clients_commandes.dto.CommandeRequest;
import com.store.gestion_clients_commandes.model.Commande;
import com.store.gestion_clients_commandes.service.CommandeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @PostMapping("/client/{clientId}")
    public Commande ajouterCommande(
            @PathVariable Long clientId,
            @Valid @RequestBody CommandeRequest commandeRequest
    ) {
        Commande commande = new Commande();
        commande.setProduit(commandeRequest.getProduit());
        commande.setQuantite(commandeRequest.getQuantite());
    	
    	return commandeService.ajouterCommande(clientId, commande);
    }

    @GetMapping
    public List<Commande> listerCommandes() {
        return commandeService.listerCommandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> recupererCommandeParId(@PathVariable Long id) {
        return commandeService.recupererCommandeParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}