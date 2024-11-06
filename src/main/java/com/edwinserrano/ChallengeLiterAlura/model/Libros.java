package com.edwinserrano.ChallengeLiterAlura.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true, nullable = false)
    private String titulo;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    private List<Autores> autor;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    private List<Traduccion> traduccion;
    @ElementCollection
    @CollectionTable(name = "categorias", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "categoria")
    private List<String> categorias;
    @ElementCollection
    @CollectionTable(name = "idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas;
    private Boolean derechosAutor;
    private String tipoDeMedio;
    @ElementCollection
    @CollectionTable(name = "formatos", joinColumns = @JoinColumn(name = "libro_id"))
    @MapKeyColumn(name = "formato_tipo")
    @Column(name = "formato_detalle")
    private Map<String, String> formatos;
    private Double numeroDeDescargas;

    public Libros(){}

    public Libros(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autor().stream()
                .map(datos -> new Autores(
                        null,
                        datos.nombre(),
                        datos.fechaDeNacimiento(),
                        datos.fechaDeMuerte()
                ))
                        .collect(Collectors.toList());
        this.traduccion = datosLibro.traduccion().stream()
                .map(datos -> new Traduccion(
                        null,
                        datos.nombre(),
                        datos.fechaDeNacimiento(),
                        datos.fechaDeMuerte()
                ))
                .collect(Collectors.toList());
        this.categorias = datosLibro.categorias();
        this.idiomas = datosLibro.idiomas();
        this.derechosAutor = datosLibro.derechosAutor();
        this.tipoDeMedio = datosLibro.tipoDeMedio();
        this.formatos = datosLibro.formatos();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autores> getAutor() {
        return autor;
    }

    public void setAutor(List<Autores> autor) {
        this.autor = autor;
    }

    public List<Traduccion> getTraduccion() {
        return traduccion;
    }

    public void setTraduccion(List<Traduccion> traduccion) {
        this.traduccion = traduccion;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Boolean getDerechosAutor() {
        return derechosAutor;
    }

    public void setDerechosAutor(Boolean derechosAutor) {
        this.derechosAutor = derechosAutor;
    }

    public String getTipoDeMedio() {
        return tipoDeMedio;
    }

    public void setTipoDeMedio(String tipoDeMedio) {
        this.tipoDeMedio = tipoDeMedio;
    }

    public Map<String, String> getFormatos() {
        return formatos;
    }

    public void setFormatos(Map<String, String> formatos) {
        this.formatos = formatos;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

}
