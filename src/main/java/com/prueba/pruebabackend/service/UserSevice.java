package com.prueba.pruebabackend.service;

import com.prueba.pruebabackend.entity.User;

import java.util.Optional;

public interface UserSevice {

    public Optional<User> getByUserName(String userName);

    public boolean existsByUserName(String userName);

    public boolean existsByEmail(String email);

    public void save(User user);
}
