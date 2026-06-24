package com.store.gestion_clients_commandes.service;

import com.store.gestion_clients_commandes.model.Client;
import com.store.gestion_clients_commandes.model.Commande;
import com.store.gestion_clients_commandes.exception.ResourceNotFoundException;
import com.store.gestion_clients_commandes.repository.ClientRepository;
import com.store.gestion_clients_commandes.repository.CommandeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandeServiceTest {

    @Mock
    private CommandeRepository commandeRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private CommandeService commandeService;

    @Test
    void ajouterCommande_doitAssocierCommandeAClient() {
        Client client = new Client();
        client.setNom("Ali");
        client.setEmail("ali@test.com");

        Commande commande = new Commande();
        commande.setProduit("Ordinateur");
        commande.setQuantite(2);

        Commande commandeSauvegardee = new Commande();
        commandeSauvegardee.setProduit("Ordinateur");
        commandeSauvegardee.setQuantite(2);
        commandeSauvegardee.setClient(client);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(commandeRepository.save(commande)).thenReturn(commandeSauvegardee);

        Commande resultat = commandeService.ajouterCommande(1L, commande);

        assertThat(resultat).isNotNull();
        assertThat(resultat.getProduit()).isEqualTo("Ordinateur");
        assertThat(resultat.getQuantite()).isEqualTo(2);
        assertThat(resultat.getClient()).isEqualTo(client);

        verify(clientRepository, times(1)).findById(1L);
        verify(commandeRepository, times(1)).save(commande);
    }

    @Test
    void ajouterCommande_doitLancerExceptionSiClientExistePas() {
        Commande commande = new Commande();
        commande.setProduit("Ordinateur");
        commande.setQuantite(2);

        when(clientRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> commandeService.ajouterCommande(99L, commande))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Client non trouvé");

        verify(clientRepository, times(1)).findById(99L);
        verify(commandeRepository, never()).save(any(Commande.class));
    }
}