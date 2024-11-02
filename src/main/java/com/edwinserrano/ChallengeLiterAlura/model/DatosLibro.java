package com.edwinserrano.ChallengeLiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true) //Ignora los campos no mapeados
public record DatosLibro(
        @JsonAlias ("title") String titulo,
        @JsonAlias ("authors") List<DatosAutor> autor,
        @JsonAlias ("translators") List<DatosTraduccion> traduccion,
        @JsonAlias("subjects") List<String> genero,
        @JsonAlias("bookshelves") List<String> categorias,
        @JsonAlias ("languages") List<String> idiomas,
        @JsonAlias ("copyright") Boolean derechosAutor,
        @JsonAlias ("media_type") String tipoDeMedio,
        @JsonAlias("formats") Map<String, String> formatos,
        @JsonAlias ("download_count") Double numeroDeDescargas
) {
    @Override
    public String titulo() {
        return titulo;
    }

    @Override
    public List<DatosAutor> autor() {
        return autor;
    }

    @Override
    public List<DatosTraduccion> traduccion() {
        return traduccion;
    }

    @Override
    public List<String> genero() {
        return genero;
    }

    @Override
    public List<String> categorias() {
        return categorias;
    }

    @Override
    public List<String> idiomas() {
        return idiomas;
    }

    @Override
    public Boolean derechosAutor() {
        return derechosAutor;
    }

    @Override
    public String tipoDeMedio() {
        return tipoDeMedio;
    }

    @Override
    public Map<String, String> formatos() {
        return formatos;
    }

    @Override
    public Double numeroDeDescargas() {
        return numeroDeDescargas;
    }
}
