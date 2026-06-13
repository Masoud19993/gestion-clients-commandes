package com.store.gestion_clients_commandes.service;

import com.store.gestion_clients_commandes.exception.ResourceNotFoundException;
import com.store.gestion_clients_commandes.repository.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void supprimerUtilisateur(Long id) {
        if (!appUserRepository.existsById(id)) {
            throw new ResourceNotFoundException("Utilisateur non trouvé avec l'id : " + id);
        }

        appUserRepository.deleteById(id);
    }
}