package com.store.gestion_clients_commandes.config;

import com.store.gestion_clients_commandes.model.AppUser;
import com.store.gestion_clients_commandes.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!appUserRepository.existsByUsername("admin")) {
            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of("ADMIN", "USER"));

            appUserRepository.save(admin);
        }

        if (!appUserRepository.existsByUsername("user")) {
            AppUser user = new AppUser();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRoles(Set.of("USER"));

            appUserRepository.save(user);
        }
    }
}