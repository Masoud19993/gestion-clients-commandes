package com.store.gestion_clients_commandes.repository;

import com.store.gestion_clients_commandes.model.Client;
import com.store.gestion_clients_commandes.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}