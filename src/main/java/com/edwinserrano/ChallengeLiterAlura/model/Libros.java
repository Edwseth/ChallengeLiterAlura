package com.edwinserrano.ChallengeLiterAlura.model;

import jakarta.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

import static com.edwinserrano.ChallengeLiterAlura.model.Categoria.convertirACategoria;

@Entity
@Table(name = "datos_libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private Double numeroDeDescargas;
    private String tipoDeMedio;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autores autor; //= new ArrayList<>(); // Inicializar la lista

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "libro_categorias", joinColumns = @JoinColumn(name = "libro_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private List<Categoria> categorias;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "idioma")
    private List<Idioma> idiomas;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "libro_id")
//    private List<Traduccion> traduccion; //= new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "libro_formatos", joinColumns = @JoinColumn(name = "libro_id"))
    @MapKeyColumn(name = "formato")
    @Column(name = "url")
    private Map<String, String> formatos = new HashMap<>();

    public Libros(){}


//        public Libros(DatosLibro datosLibro){
//        this.titulo = datosLibro.getTitulo();
//        this.autor = autor;
////        this.autor = datosLibro.autor().stream()
////                .map(datos -> new Autores(
////                        null,
////                        datos.nombre(),
////                        datos.fechaDeNacimiento(),
////                        datos.fechaDeMuerte()
////                ))
////                        .collect(Collectors.toList());
//        this.traduccion = datosLibro.traduccion().stream()
//                .map(datos -> new Traduccion(
//                        null,
//                        datos.nombre(),
//                        datos.fechaDeNacimiento(),
//                        datos.fechaDeMuerte()
//                ))
//                .collect(Collectors.toList());
//        this.categorias = datosLibro.categorias().stream()
//                .map(Categoria::valueOf)
//                .findFirst()
//                .orElse(null);
//        this.idiomas = datosLibro.idiomas().stream()
//                .map(Idioma::valueOf)
//                .findFirst()
//                .orElse(null);
//        this.tipoDeMedio = (String) datosLibro.getTipoDeMedio();
//        this.formatos = (Map<String, String>) datosLibro.getFormatos();
//        this.numeroDeDescargas = (Double) datosLibro.getNumeroDeDescargas();
//    }


    public Libros(DatosLibro datosLibro, Autores autores) {

        this.titulo = datosLibro.getTitulo();
        this.numeroDeDescargas = (Double) datosLibro.getNumeroDeDescargas();
        this.tipoDeMedio = (String) datosLibro.getTipoDeMedio();
        this.autor = autores;
        Object categoriasObj = datosLibro.getCategorias();  // Recibe como Object.
        if (categoriasObj instanceof List) {
            List<String> categoriasList = (List<String>) categoriasObj;  // Casting seguro a List<String>.
            if (!categoriasList.isEmpty()) {
                String categoriaStr = categoriasList.get(0);  // Toma el primer elemento de la lista.
                this.categorias = Collections.singletonList(convertirACategoria(categoriaStr));  // Convierte a enum.
            }
        } else {
            System.out.println("Error: categorias no es una lista de Strings.");
        }
//        String categoriaStr = datosLibro.getCategorias();
//        this.categorias = Collections.singletonList(convertirACategoria(categoriaStr));
        //this.categorias = (List<Categoria>) datosLibro.getCategorias();
        Object idiomasObj = datosLibro.getIdiomas();
        if (idiomasObj instanceof List) {
            List<String> idiomasList = (List<String>) idiomasObj;  // Casting seguro a List<String>.
            this.idiomas = convertirAIdioma(idiomasList);  // Aquí llamas a tu método de conversión para Idiomas.
        } else {
            System.out.println("Error: idiomas no es una lista de Strings.");
        }
//        List<String> idiomaStrings = (List<String>) datosLibro.getIdiomas();
//        this.idiomas = convertirAIdioma(idiomaStrings);
        //this.idiomas = (List<Idioma>) datosLibro.getIdiomas();  // O lo que corresponda
//        this.traduccion = (List<Traduccion>) datosLibro.getTraduccion();  // O lo que corresponda
        this.formatos = (Map<String, String>) datosLibro.getFormatos();

    }

    private List<Idioma> convertirAIdioma(List<String> idiomaStrings) {
        List<Idioma> idiomas = new ArrayList<>();
        for (String idiomaStr : idiomaStrings) {
            try {
                Idioma idioma = Idioma.fromCodigo(idiomaStr);
                //Idioma idioma = Idioma.valueOf(idiomaStr.toUpperCase());
                idiomas.add(idioma);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                //System.out.println("Idioma no válido: " + idiomaStr);
            }
        }
        return idiomas;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

//    public List<Traduccion> getTraduccion(List<DatosTraduccion> traduccion) {
//        return this.traduccion;
//    }
//
//    public void setTraduccion(List<Traduccion> traduccion) {
//        this.traduccion = traduccion;
//    }

    public List<Categoria> getCategorias() {
        return this.categorias;
    }

    public void setCategorias(Categoria categorias) {
        this.categorias = Collections.singletonList(categorias);
    }

    public List<Idioma> getIdiomas(Object idiomas) {
        return this.idiomas;
    }

    public void setIdiomas(Idioma idiomas) {
        this.idiomas = Collections.singletonList(idiomas);
    }

    public String getTipoDeMedio(Object tipoDeMedio) {
        return this.tipoDeMedio;
    }

    public void setTipoDeMedio(String tipoDeMedio) {
        this.tipoDeMedio = tipoDeMedio;
    }

    public Map<String, String> getFormatos(Object formatos) {
        return this.formatos;
    }

    public void setFormatos(Map<String, String> formatos) {
        this.formatos = formatos;
    }

    public Double getNumeroDeDescargas(Object formatos) {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

//    @Override
//    public String toString() {
//        String idiomasStr = idiomas.stream()
//                .map(Idioma::getNombre)  // Utiliza el nombre en español del enum
//                .collect(Collectors.joining(", "));
////        String autoresStr = autor != null && !autor.isEmpty()
////                ? autor.stream()
////                .map(autor -> autor.getNombre())  // Asegúrate de que 'getNombre' devuelve un String
////                .collect(Collectors.joining(", "))  // Concatena los nombres con una coma
////                : "Sin autor";  // Si no hay autores, muestra "Sin autor"
//        return  "Titulo= " + this.titulo + '\'' +
//                "\nNúmero De Descargas: " + this.numeroDeDescargas +
//                "\ntipoDeMedio: " + this.tipoDeMedio + '\'' +
//                "\nautor: " + this.autor +
//                "\ncategorias: " + this.categorias +
//                "\nidiomas: " + idiomasStr +
////                "\ntraduccion: " + this.traduccion +
//                "\nformatos: " + this.formatos;
//    }



}
