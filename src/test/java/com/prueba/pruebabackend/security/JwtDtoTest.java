package com.prueba.pruebabackend.security;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import com.prueba.pruebabackend.security.dto.JwtDto;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class JwtDtoTest {

    @Test
    void testJwtDto() {
        // Arrange
        String token = "example-token";
        String userName = "john_doe";
        Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                new GrantedAuthorityImpl("ROLE_USER"),
                new GrantedAuthorityImpl("ROLE_ADMIN")
        );

        JwtDto jwtDto = new JwtDto(token, userName, authorities);

        assertEquals(token, jwtDto.getToken());
        assertEquals("Bearer", jwtDto.getBearer());
        assertEquals(userName, jwtDto.getUserName());
        assertEquals(authorities, jwtDto.getAuthorities());
    }

    private static class GrantedAuthorityImpl implements GrantedAuthority {
        private String authority;

        public GrantedAuthorityImpl(String authority) {
            this.authority = authority;
        }

        @Override
        public String getAuthority() {
            return authority;
        }
    }
}
