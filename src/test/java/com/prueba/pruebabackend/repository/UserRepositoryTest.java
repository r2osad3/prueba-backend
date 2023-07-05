package com.prueba.pruebabackend.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.prueba.pruebabackend.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserName() {
        // Crear un usuario y guardarlo en la base de datos
        User user = new User();
        user.setUserName("john_doe");
        userRepository.save(user);

        // Buscar el usuario por nombre de usuario
        Optional<User> foundUser = userRepository.findByUserName("john_doe");

        // Verificar que se haya encontrado el usuario correctamente
        Assertions.assertTrue(foundUser.isPresent());
        assertEquals("john_doe", foundUser.get().getUserName());
    }

    @Test
    public void testExistsByUserName() {
        // Crear un usuario y guardarlo en la base de datos
        User user = new User();
        user.setUserName("john_doe");
        userRepository.save(user);

        // Verificar si existe un usuario con el nombre de usuario dado
        boolean exists = userRepository.existsByUserName("john_doe");

        // Verificar que se haya encontrado el usuario correctamente
        Assertions.assertTrue(exists);
    }

    @Test
    public void testExistsByEmail() {
        // Crear un usuario y guardarlo en la base de datos
        User user = new User();
        user.setEmail("john.doe@example.com");
        userRepository.save(user);

        // Verificar si existe un usuario con el correo electrónico dado
        boolean exists = userRepository.existsByEmail("john.doe@example.com");

        // Verificar que se haya encontrado el usuario correctamente
        Assertions.assertTrue(exists);
    }
}
