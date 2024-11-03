package com.edwinserrano.ChallengeLiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return categorias.stream()
                .map(categoria -> {
                    if (categoria.startsWith("Browsing: ")) {
                        return categoria.split(": ")[1]; // Extrae solo la parte después de "Browsing: "
                    }
                    return categoria; // Deja las otras categorías tal como están
                })
                .collect(Collectors.toList());
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

    @Override
    public String toString() {
        String genero = categorias.isEmpty() ? "Sin género" : categorias.get(0);
        // Si la categoría comienza con "Browsing: ", extrae la parte relevante
        if (genero.startsWith("Browsing: ")) {
            genero = genero.split(": ")[1];
        }
        String autores = autor.isEmpty() ? "Sin autor" : autor.get(0).nombre(); // Asumiendo que tienes un método nombre() en DatosAutor
        double descargas = numeroDeDescargas != null ? numeroDeDescargas : 0.0; // Valor predeterminado si es null
        return String.format("Título: %s\nAutor(es): %s\nIdioma(s): %s\nGénero: %s\nNúmero de Descargas: %.0f",
                titulo,
                autores,
                String.join(", ", idiomas),
                genero,
                descargas);
    }
}
