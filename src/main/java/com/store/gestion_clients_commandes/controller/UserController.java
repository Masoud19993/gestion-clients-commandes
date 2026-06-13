package com.store.gestion_clients_commandes.controller;

import com.store.gestion_clients_commandes.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
        appUserService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}