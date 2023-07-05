package com.prueba.pruebabackend.services;
import static com.prueba.pruebabackend.entity.RoleName.ROLE_ADMIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.prueba.pruebabackend.entity.Role;
import com.prueba.pruebabackend.repository.RoleRepository;
import com.prueba.pruebabackend.service.serviceImpl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetByRoleName() {
        Role role = new Role();
        role.setRolName(ROLE_ADMIN);
        when(roleRepository.findByRolName(ROLE_ADMIN)).thenReturn(Optional.of(role));
        Optional<Role> result = roleService.getByRoleName(ROLE_ADMIN);
        verify(roleRepository, times(1)).findByRolName(ROLE_ADMIN);
        assertTrue(result.isPresent());
        assertEquals(ROLE_ADMIN, result.get().getRolName());
    }

    @Test
    public void testSave() {
        Role role = new Role();
        role.setRolName(ROLE_ADMIN);
        roleService.save(role);
        verify(roleRepository, times(1)).save(role);
    }

}
