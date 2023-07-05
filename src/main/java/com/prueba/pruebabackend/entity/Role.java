package com.prueba.pruebabackend.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName rolName;

    public Role(RoleName rolName) {
        this.rolName = rolName;
    }

    public Role() {

    }

    public Long getId() {
        return id;
    }

    public Role(Long id, RoleName rolName) {
        this.id = id;
        this.rolName = rolName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRolName() {
        return rolName;
    }

    public void setRolName(RoleName rolName) {
        this.rolName = rolName;
    }


}
