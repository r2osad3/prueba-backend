package com.prueba.pruebabackend.util;


import com.prueba.pruebabackend.entity.Role;
import com.prueba.pruebabackend.entity.RoleName;
import com.prueba.pruebabackend.service.serviceImpl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleServiceImpl rolService;

    @Override
    public void run(String... args) throws Exception {

        Role rolAdmin = new Role(RoleName.ROLE_ADMIN);
        Role rolUser = new Role(RoleName.ROLE_USER);
         rolService.save(rolAdmin);
         rolService.save(rolUser);



    }
}