package com.multinacional.core.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departamento")
public class Departamento implements Serializable {

    private static final long serialVersionUID = -8533711227346499257L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @ManyToMany
    @JoinTable(name = "empresadep",
            joinColumns = @JoinColumn(name = "codDepartamento"),
            inverseJoinColumns = @JoinColumn(name="codEmpresa"))
    private Set<Empresa> listaEmpresa;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmpleadoDep> empleadoDep;
}
