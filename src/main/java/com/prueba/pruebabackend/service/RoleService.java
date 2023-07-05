package com.prueba.pruebabackend.service;

import com.prueba.pruebabackend.entity.Role;
import com.prueba.pruebabackend.entity.RoleName;

import java.util.Optional;

public interface RoleService {

    public Optional<Role> getByRoleName(RoleName roleName);

    public void save(Role role);
}
