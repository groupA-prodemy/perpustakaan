package com.example.perpustakaan.repository;

import com.example.SistemPerpustakaan.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByUsername(String username);

    Optional<Admin> findByPassword(String password);

    Optional<Admin> findByUsernameAndPassword(String username, String password);
}
