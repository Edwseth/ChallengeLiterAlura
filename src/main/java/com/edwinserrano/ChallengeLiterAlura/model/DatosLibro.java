package com.edwinserrano.ChallengeLiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true) //Ignora los campos no mapeados
public record DatosLibro(
        @JsonAlias ("title") String titulo,
        @JsonProperty
        @JsonAlias ("authors") List<DatosAutor> autor,
        @JsonProperty
        @JsonAlias ("translators") List<DatosTraduccion> traduccion,
        @JsonProperty
        @JsonAlias("bookshelves") List<String> categorias,
        @JsonProperty
        @JsonAlias ("languages") List<String> idiomas,
        @JsonAlias ("copyright") Boolean derechosAutor,
        @JsonAlias ("media_type") String tipoDeMedio,
        @JsonAlias("formats") Map<String, String> formatos,
        @JsonProperty
        @JsonAlias ("download_count") Double numeroDeDescargas
) {

    public String getTitulo() {
        return titulo;
    }

    public Object getCategorias() {
        return categorias;
    }
//
    public Object getIdiomas() {
        return idiomas;
    }
//
    public Object getTipoDeMedio() {
        return tipoDeMedio;
    }
//
    public Object getFormatos() {
        return formatos;
    }

    public Object getTraduccion() {
        return traduccion;
    }

    public Object getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public Optional<Object> stream() {
        return Optional.ofNullable(autor);
    }

    public String getAutor() {
        return autor.toString();
    }

    @Override
    public String toString() {
        String autorLimpio = autor.stream()
                .map(autor -> autor.toString()) // O autor.getNombre() si es apropiado
                .collect(Collectors.joining(", "));
        // Elimina "Browsing: " de cada categoría antes de mostrarla
        String categoriasLimpias = categorias.stream()
                .map(categoria -> categoria.replace("Browsing: ", ""))
                .collect(Collectors.joining(", "));
        return "Titulo= " + titulo +
                "\nAutor= " + autorLimpio +
                "\nGénero= " + categoriasLimpias +
                "\nNúmero de Descargas= " + numeroDeDescargas;
    }
}
