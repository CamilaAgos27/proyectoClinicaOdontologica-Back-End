package com.dh.proyectoOdontologico.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

// debemos indicar que es una identidad, va arriba porque queremos decir que lo que haya abajo es una entidad.
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id // genera una id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // que se autogenere el id, no lo tengo que pasar
    private Long id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private Integer dni;
    @Column
    private LocalDate fechaAlta;
    @Column
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
     /* Las relaciones entre entidades a menudo dependen de la existencia de otra entidad, por ejemplo la relación Persona-Dirección. Sin la Persona,
    la entidad Dirección no tiene ningún significado propio. Cuando eliminamos la entidad Persona,
    nuestra entidad Dirección también debe ser eliminada.La cascada es la manera de lograr esto. Cuando realizamos alguna acción en la entidad destino,
    la misma acción se aplicará a la entidad asociada.*/
    /* CascadeType.ALL propaga todas las operaciones -incluyendo las específicas de Hibernate- de una entidad padre a una entidad hija.*/
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    /* se utiliza para especificar una columna para unir una asociación de entidades o una colección de elementos.
    Esta anotación indica que la entidad adjunta es la propietaria de la relación y que la tabla correspondiente
    tiene una columna de clave externa que hace referencia a la tabla de la parte no propietaria.*/
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turno = new HashSet<>();
    public Set<Turno> getTurno() {
        return turno;
    }
    public void setTurno(Set<Turno> turnos) {
        this.turno = turno;
    }

    public Paciente(String nombre, String apellido, Integer dni, LocalDate fechaAlta, String email, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
        this.email = email;
        this.domicilio = domicilio;
    }

    public Paciente(){

    }

    public Paciente(Long id, String nombre, String apellido, Integer dni, LocalDate fechaAlta, String email, Domicilio domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaAlta = fechaAlta;
        this.email = email;
        this.domicilio = domicilio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
}
