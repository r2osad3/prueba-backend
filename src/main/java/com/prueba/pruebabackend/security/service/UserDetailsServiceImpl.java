package com.prueba.pruebabackend.security.service;

import com.prueba.pruebabackend.entity.PrincipalUser;
import com.prueba.pruebabackend.entity.User;
import com.prueba.pruebabackend.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserServiceImpl usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        User usuario = usuarioService.getByUserName(nombreUsuario).get();
        return PrincipalUser.build(usuario);
    }

    public UserServiceImpl getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UserServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }
}

