package com.edwinserrano.ChallengeLiterAlura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "datos_autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String nombre;
    @Column(name = "birth_year")
    private Integer fechaDeNacimiento;
    @Column(name = "death_year")
    private Integer fechaDeMuerte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)//@ManeToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private List<Libros> libros;

    public Autores(){}

    public Autores(String nombre, Integer fechaDeNacimiento, Integer fechaDeMuerte) { //public Autores(Long id, String nombre, Integer fechaDeNacimiento, Integer fechaDeMuerte) {
        //this.id = id;
        this.nombre = nombre != null ? nombre : "Autor Desconocido";
        this.fechaDeNacimiento = Optional.ofNullable(fechaDeNacimiento).orElse(0);
        this.fechaDeMuerte = Optional.ofNullable(fechaDeMuerte).orElse(0);
    }


//    public List<Libros> getLibros() {return libros;}
//
//    public void setLibros(List<Libros> libros) {this.libros = libros;}

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    @Override
    public String toString() {
        return "Autores{" +
                "nombre='" + nombre + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", fechaDeMuerte=" + fechaDeMuerte +
                '}';
    }

//    @Override
//    public boolean esIgual(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Autores autores = (Autores) o;
//        return fechaDeNacimiento == autores.fechaDeNacimiento && fechaDeMuerte == autores.fechaDeMuerte && Object.esIgual(id, autores.id) && Object.esIgual(nombre, autores.nombre);
//    }
}
