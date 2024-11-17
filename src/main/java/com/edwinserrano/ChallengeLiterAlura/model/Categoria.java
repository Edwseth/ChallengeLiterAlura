package com.edwinserrano.ChallengeLiterAlura.model;

public enum Categoria {
    ACCION("Browsing: Action", "Acción"),
    FICCION("Browsing: Fiction", "Ficción"),
    LITERATURA("Browsing: Literature", "Literatura"),
    POESIA("Browsing: Poetry", "Poesia"),
    CULTURA_CIVILIZACION_SOCIEDAD("Browsing: Culture/Civilization/Society", "Cultura/Civilización/Sociedad"),
    HUMOR("Browsing: Humour", "Humor"),
    DRAMA("Browsing: Drama", "Drama"), //reviasr
    HISTORIA("Browsing: History", "Historia"),
    POLITICA("Browsing: Politics", "Politica"),
    CRIMEN("Browsing: Crime", "Crimen"); //revisar

    private String categoriaOmdb;
    private String categoriaEspanol;
    Categoria(String categoriaOmdb, String categoriaEspanol) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEspanol = categoriaEspanol;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

    public static Categoria fromEspanol(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaEspanol.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

    public static Categoria convertirACategoria(String categoriaStr) {
        try {
            return fromString(categoriaStr);  // Usando el método fromString existente
        } catch (IllegalArgumentException e) {
            System.out.println("Categoría no válida: " + categoriaStr);
            return null;
        }
    }
}
