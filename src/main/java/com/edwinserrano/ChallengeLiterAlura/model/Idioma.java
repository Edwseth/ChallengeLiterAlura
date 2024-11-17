package com.edwinserrano.ChallengeLiterAlura.model;

public enum Idioma {
    ESPAÑOL("es", "Español"),
    INGLES("en", "Inglés"),
    FRANCES("fr", "Francés"),
    ALEMAN("de", "Alemán"),
    ITALIANO("it", "Italiano"),
    PORTUGUES("pt", "Portugués"),
    RUSO("ru", "Ruso"),
    CHINO("zh", "Chino"),
    JAPONES("ja", "Japonés"),
    ARABE("ar", "Árabe"),
    HOLANDES("nl", "Holandés"),
    HUNGARO("hu","Húngaro"),
    GRIEGO("el", "Griego"),
    SUECO("sv", "Sueco"),
    NORUEGO("no", "Noruego"),
    DANES("da", "Danés"),
    FINES("fi", "Finés");

    private final String codigo;
    private final String nombre;

    Idioma(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }

    // Método estático para encontrar Idioma por su código
    public static Idioma fromCodigo(String codigo) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.getCodigo().equalsIgnoreCase(codigo)) {
                return idioma;
            }
        }
        // Retornar null o lanzar una excepción si no se encuentra
        throw new IllegalArgumentException("Idioma no válido: " + codigo);
    }

}
