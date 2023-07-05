package com.prueba.pruebabackend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.prueba.pruebabackend.entity.User;
import com.prueba.pruebabackend.repository.UserRepository;
import com.prueba.pruebabackend.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testGetByUserName() {
        User user = new User();
        user.setUserName("junR");
        when(userRepository.findByUserName("junR")).thenReturn(Optional.of(user));
        Optional<User> result = userService.getByUserName("junR");
        verify(userRepository, times(1)).findByUserName("junR");
        assertTrue(result.isPresent());
        assertEquals("junR", result.get().getUserName());
    }

    @Test
    public void testExistsByUserName() {
        when(userRepository.existsByUserName("john.doe")).thenReturn(true);
        assertTrue(userService.existsByUserName("john.doe"));
        verify(userRepository, times(1)).existsByUserName("john.doe");
    }

    @Test
    public void testExistsByEmail() {
        when(userRepository.existsByEmail("rosa@gmail.com")).thenReturn(true);
        assertTrue(userService.existsByEmail("rosa@gmail.com"));
        verify(userRepository, times(1)).existsByEmail("rosa@gmail.com");
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUserName("juan");
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }
}
