package com.edwinserrano.ChallengeLiterAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosConsulta(List<DatosLibro> consultas) {
    }
