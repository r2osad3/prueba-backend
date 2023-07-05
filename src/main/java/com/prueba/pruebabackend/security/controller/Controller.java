package com.prueba.pruebabackend.security.controller;
import com.prueba.pruebabackend.entity.Role;
import com.prueba.pruebabackend.entity.RoleName;
import com.prueba.pruebabackend.entity.User;
import com.prueba.pruebabackend.entity.Vacancy;
import com.prueba.pruebabackend.security.dto.JwtDto;
import com.prueba.pruebabackend.security.dto.LoginUsuario;
import com.prueba.pruebabackend.security.dto.Mensaje;
import com.prueba.pruebabackend.security.dto.NewUser;
import com.prueba.pruebabackend.security.jwt.JwtProvider;
import com.prueba.pruebabackend.service.serviceImpl.RoleServiceImpl;
import com.prueba.pruebabackend.service.serviceImpl.UserServiceImpl;
import com.prueba.pruebabackend.service.serviceImpl.VacancyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImpl usuarioService;

    @Autowired
    RoleServiceImpl rolService;

    @Autowired
    JwtProvider jwtProvider;


    //PAra las vacantes
    @Autowired
    VacancyServiceImpl vacancyService;

    @PostMapping("/new")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByUserName(nuevoUsuario.getUserName()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        User usuario =
                new User(nuevoUsuario.getName(), nuevoUsuario.getUserName(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(rolService.getByRoleName(RoleName.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRoleName(RoleName.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        System.out.println("esta entrando");
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUserName(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }


    @GetMapping("/lista")
    public ResponseEntity<List<Vacancy>> getVacancies(){

        System.out.println("entro al metodod");
        List<Vacancy> vacantes = vacancyService.list();
        if (vacantes.isEmpty()){
            System.out.println("vacia");
        }else{System.out.println("cargada");}
        return new ResponseEntity<>(vacantes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        if(!vacancyService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        vacancyService.delete(id);
        return new ResponseEntity(new Mensaje("vacante eliminado"), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Vacancy vacancy){

        vacancyService.save(vacancy);
        return new ResponseEntity(new Mensaje("Vacante creada"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Vacancy> getById(@PathVariable Long id){
        if(!vacancyService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Vacancy vacancy = vacancyService.getOne(id).get();
        return new ResponseEntity(vacancy, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")Long id, @RequestBody Vacancy vacancy){
        if(!vacancyService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);



        vacancyService.save(vacancy);
        return new ResponseEntity(new Mensaje("vacante actualizado"), HttpStatus.OK);
    }
}