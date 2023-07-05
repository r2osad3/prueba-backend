package com.prueba.pruebabackend.security;
import com.prueba.pruebabackend.security.dto.NewUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NewUserTest {

    @Test
    public void testNewUserFields() {
        // Create a new user object
        NewUser user = new NewUser();

        user.setName("marta");
        user.setUserName("mar");
        user.setEmail("mar@example.com");
        user.setPassword("password");
        user.getRoles().add("ROLE_USER");

        Assertions.assertEquals("marta", user.getName());
        Assertions.assertEquals("mar", user.getUserName());
        Assertions.assertEquals("mar@example.com", user.getEmail());
        Assertions.assertEquals("password", user.getPassword());
        Assertions.assertTrue(user.getRoles().contains("ROLE_USER"));
    }




}
