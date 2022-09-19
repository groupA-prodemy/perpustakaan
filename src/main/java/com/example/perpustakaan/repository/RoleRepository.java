package com.example.perpustakaan.repository;

import com.example.perpustakaan.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleId(Integer roleId);

    Optional<Role> findByRoleName(String roleName);
}
