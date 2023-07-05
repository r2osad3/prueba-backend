package com.prueba.pruebabackend.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;

import com.prueba.pruebabackend.entity.User;
import com.prueba.pruebabackend.security.service.UserDetailsServiceImpl;
import com.prueba.pruebabackend.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class UserDetailsServiceImplTest {

    @Test
    void testLoadUserByUsername_ExistingUser() {
        String nombreUsuario = "ros";
        User usuario = new User();
        usuario.setUserName(nombreUsuario);
        usuario.setPassword("password123");
        UserServiceImpl usuarioService = mock(UserServiceImpl.class);
        when(usuarioService.getByUserName(nombreUsuario)).thenReturn(Optional.of(usuario));

        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setUsuarioService(usuarioService);

        UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario);

        assertNotNull(userDetails);
        assertEquals(nombreUsuario, userDetails.getUsername());
        assertEquals(usuario.getPassword(), userDetails.getPassword());

    }

}
