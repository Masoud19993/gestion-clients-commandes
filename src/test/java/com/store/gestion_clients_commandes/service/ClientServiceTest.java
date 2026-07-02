package com.store.gestion_clients_commandes.service;

import com.store.gestion_clients_commandes.model.Client;
import com.store.gestion_clients_commandes.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void ajouterClient_doitSauvegarderClient() {
        Client client = new Client();
        client.setNom("Ali");
        client.setEmail("ali@test.com");

        Client clientSauvegarde = new Client();
        clientSauvegarde.setNom("Ali");
        clientSauvegarde.setEmail("ali@test.com");

        when(clientRepository.save(client)).thenReturn(clientSauvegarde);

        Client resultat = clientService.ajouterClient(client);

        assertThat(resultat).isNotNull();
        assertThat(resultat.getNom()).isEqualTo("Ali");
        assertThat(resultat.getEmail()).isEqualTo("ali@test.com");

        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void listerClients_doitRetournerListeClients() {
        Client client1 = new Client();
        client1.setNom("Ali");
        client1.setEmail("ali@test.com");

        Client client2 = new Client();
        client2.setNom("Sara");
        client2.setEmail("sara@test.com");

        when(clientRepository.findAll()).thenReturn(List.of(client1, client2));

        List<Client> resultat = clientService.listerClients();

        assertThat(resultat).hasSize(2);
        assertThat(resultat.get(0).getNom()).isEqualTo("Ali");
        assertThat(resultat.get(1).getNom()).isEqualTo("Sara");

        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void recupererClientParId_doitRetournerClientSiExiste() {
        Client client = new Client();
        client.setNom("Ali");
        client.setEmail("ali@test.com");

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Optional<Client> resultat = clientService.recupererClientParId(1L);

        assertThat(resultat).isPresent();
        assertThat(resultat.get().getNom()).isEqualTo("Ali");

        verify(clientRepository, times(1)).findById(1L);
    }
}