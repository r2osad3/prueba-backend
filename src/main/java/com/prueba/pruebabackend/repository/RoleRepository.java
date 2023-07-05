package com.prueba.pruebabackend.repository;

import com.prueba.pruebabackend.entity.Role;
import com.prueba.pruebabackend.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
        Optional<Role> findByRolName(RoleName roleName);
}
