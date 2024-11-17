//package com.edwinserrano.ChallengeLiterAlura.model;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "datos_traductores")
//public class Traduccion {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "name")
//    private String nombre;
//
//    @Column(name = "birth_year")
//    private Integer fechaDeNacimiento;
//
//    @Column(name = "death_year")
//    private Integer fechaDeMuerte;
//
//    @ManyToOne(fetch = FetchType.EAGER)  // Many traducciones a un libro
//    @JoinColumn(name = "libro_id")  // La columna en la tabla traduccion que hace referencia a libro
//    private Libros libros;
//
//    public Traduccion (){}
//
//    public Traduccion(Long id, String nombre, Integer fechaDeNacimiento, Integer fechaDeMuerte) {
//        this.id = id;
//        this.nombre = nombre;
//        this.fechaDeNacimiento = fechaDeNacimiento;
//        this.fechaDeMuerte = fechaDeMuerte;
//    }
//
//    public Libros getLibros() {
//        return libros;
//    }
//
//    public void setLibros(Libros libros) {
//        this.libros = libros;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public Integer getFechaDeNacimiento() {
//        return fechaDeNacimiento;
//    }
//
//    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
//        this.fechaDeNacimiento = fechaDeNacimiento;
//    }
//
//    public Integer getFechaDeMuerte() {
//        return fechaDeMuerte;
//    }
//
//    public void setFechaDeMuerte(Integer fechaDeMuerte) {
//        this.fechaDeMuerte = fechaDeMuerte;
//    }
//}
