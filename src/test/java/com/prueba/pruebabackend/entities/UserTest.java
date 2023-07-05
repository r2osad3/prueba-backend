package com.prueba.pruebabackend.entities;
import com.prueba.pruebabackend.entity.Role;
import com.prueba.pruebabackend.entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.prueba.pruebabackend.entity.RoleName;

public class UserTest {

    @Test
    public void testUserGetterAndSetter() {
        User user = new User();
        user.setName("Marisela");
        user.setUserName("Mar");
        user.setEmail("mari@gmail.com");
        user.setPassword("mar1234");

        assertEquals("Marisela", user.getName());
        assertEquals("Mar", user.getUserName());
        assertEquals("mari@gmail.com", user.getEmail());
        assertEquals("mar1234", user.getPassword());
    }

    @Test
    public void testUserRoles() {
        User user = new User();

        Role role1 = new Role();
        role1.setRolName(RoleName.ROLE_ADMIN);

        Role role2 = new Role();
        role2.setRolName(RoleName.ROLE_USER);

        user.getRoles().add(role1);
        user.getRoles().add(role2);

        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains(role1));
        assertTrue(user.getRoles().contains(role2));
    }


}
