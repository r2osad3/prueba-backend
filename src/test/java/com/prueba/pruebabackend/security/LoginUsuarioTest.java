package com.prueba.pruebabackend.security;

import com.prueba.pruebabackend.security.dto.LoginUsuario;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginUsuarioTest {

    @Test
    void testLoginUsuario_Valid() {
        LoginUsuario loginUsuario = new LoginUsuario();
        loginUsuario.setUserName("john_doe");
        loginUsuario.setPassword("password123");

        Set<ConstraintViolation<LoginUsuario>> violations = validate(loginUsuario);

        assertTrue(violations.isEmpty());
    }

    private Set<ConstraintViolation<LoginUsuario>> validate(LoginUsuario loginUsuario) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(loginUsuario);
    }
}
