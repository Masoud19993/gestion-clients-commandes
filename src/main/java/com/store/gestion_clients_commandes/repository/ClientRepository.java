package com.store.gestion_clients_commandes.repository;

import com.store.gestion_clients_commandes.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}