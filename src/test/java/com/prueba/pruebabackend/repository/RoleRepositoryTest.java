package com.prueba.pruebabackend.repository;

import static com.prueba.pruebabackend.entity.RoleName.ROLE_ADMIN;
import static com.prueba.pruebabackend.entity.RoleName.ROLE_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.prueba.pruebabackend.entity.Role;
import com.prueba.pruebabackend.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.List;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testFindAllRoles() {
        // Crear varios roles y guardarlos en la base de datos
        Role role1 = new Role();
        role1.setRolName(ROLE_ADMIN);
        roleRepository.save(role1);

        Role role2 = new Role();
        role2.setRolName(ROLE_USER);
        roleRepository.save(role2);

        List<Role> roles = roleRepository.findAll();

        assertEquals(2, roles.size());
    }

    @Test
    public void testFindByName() {
        Role role = new Role();
        role.setRolName(ROLE_ADMIN);
        roleRepository.save(role);
        Optional<Role> foundRole = roleRepository.findByRolName(ROLE_ADMIN);
        Assertions.assertTrue(foundRole.isPresent());
        assertEquals(ROLE_ADMIN, foundRole.get().getRolName());
    }

    @Test
    public void testSaveRole() {
        Role role = new Role();
        role.setRolName(ROLE_ADMIN);

        Role savedRole = roleRepository.save(role);

        assertEquals(ROLE_ADMIN, savedRole.getRolName());
    }
}
