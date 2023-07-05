package com.prueba.pruebabackend.controllers;
import java.util.Optional;

import com.prueba.pruebabackend.controller.UserController;
import com.prueba.pruebabackend.entity.User;
import com.prueba.pruebabackend.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetUserByUserName() {
        User user = new User();
        user.setUserName("testUser");

        when(userService.getByUserName("testUser")).thenReturn(Optional.of(user));
        ResponseEntity<User> response = userController.getUserByUserName("testUser");
        verify(userService, times(1)).getByUserName("testUser");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetUserByUserName_NotFound() {
        when(userService.getByUserName("nonExistingUser")).thenReturn(Optional.empty());
        ResponseEntity<User> response = userController.getUserByUserName("nonExistingUser");
        verify(userService, times(1)).getByUserName("nonExistingUser");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateUser_Success() {
        User user = new User();
        user.setUserName("her");
        user.setEmail("her@google.com");
        when(userService.existsByUserName("her")).thenReturn(false);
        when(userService.existsByEmail("her@google.com")).thenReturn(false);
        ResponseEntity<String> response = userController.createUser(user);

        verify(userService, times(1)).existsByUserName("her");
        verify(userService, times(1)).existsByEmail("her@google.com");
        verify(userService, times(1)).save(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\":\"Usuario creado exitosamente\"}", response.getBody());
    }

    @Test
    public void testCreateUser_UserNameInUse() {
        // Crear un usuario de prueba
        User user = new User();
        user.setUserName("existingUser");
        user.setEmail("test@example.com");

        // Configurar el servicio para que encuentre un usuario existente con el mismo nombre de usuario
        when(userService.existsByUserName("existingUser")).thenReturn(true);

        // Obtener la respuesta del controlador al crear el usuario
        ResponseEntity<String> response = userController.createUser(user);

        // Verificar que se haya llamado al método existsByUserName() del servicio
        verify(userService, times(1)).existsByUserName("existingUser");

        // Verificar que no se haya llamado al método existsByEmail() del servicio
        verify(userService, never()).existsByEmail(anyString());

        // Verificar que no se haya llamado al método save() del servicio
        verify(userService, never()).save(any(User.class));

        // Verificar que se haya devuelto el código de estado "Bad Request" en la respuesta
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"error\":\"El nombre de usuario ya está en uso\"}", response.getBody());
    }

    @Test
    public void testCreateUser_EmailRegistered() {
        User user = new User();
        user.setUserName("testUser");
        user.setEmail("registered@example.com");
        when(userService.existsByUserName("testUser")).thenReturn(false);
        when(userService.existsByEmail("registered@example.com")).thenReturn(true);
        ResponseEntity<String> response = userController.createUser(user);
        verify(userService, times(1)).existsByUserName("testUser");
        verify(userService, times(1)).existsByEmail("registered@example.com");
        verify(userService, never()).save(any(User.class));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"error\":\"El correo electrónico ya está registrado\"}", response.getBody());
    }
}
