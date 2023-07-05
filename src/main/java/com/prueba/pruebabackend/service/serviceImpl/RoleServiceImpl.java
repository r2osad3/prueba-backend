package com.prueba.pruebabackend.service.serviceImpl;


import com.prueba.pruebabackend.entity.Role;
import com.prueba.pruebabackend.entity.RoleName;
import com.prueba.pruebabackend.repository.RoleRepository;
import com.prueba.pruebabackend.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> getByRoleName(RoleName roleName) {
        return roleRepository.findByRolName(roleName);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
