package com.edwinserrano.ChallengeLiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaDeNacimiento,
        @JsonAlias("death_year") Integer fechaDeMuerte
) {
    @Override
    public String toString() {
        // Comprueba si el nombre es nulo o vacío y muestra "Sin autor" si es necesario
        String nombreAutor = nombre != null && !nombre.isEmpty() ? nombre : "Sin autor";
        return String.format("Nombre: %s\nAño de nacimiento: %s\nAño de muerte: %s" ,
                nombre != null ? nombre : "Sin nombre",
                fechaDeNacimiento != null ? fechaDeNacimiento : "Desconocido",
                fechaDeMuerte != null ? fechaDeMuerte : "Desconocido");
    }
}

