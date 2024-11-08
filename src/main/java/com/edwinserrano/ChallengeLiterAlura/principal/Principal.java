package com.edwinserrano.ChallengeLiterAlura.principal;

import com.edwinserrano.ChallengeLiterAlura.model.Datos;
import com.edwinserrano.ChallengeLiterAlura.model.DatosAutor;
import com.edwinserrano.ChallengeLiterAlura.model.DatosLibro;
import com.edwinserrano.ChallengeLiterAlura.service.ConsumoAPI;
import com.edwinserrano.ChallengeLiterAlura.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?";
    private final String URL_SEARCH = "search=";
    private final String URL_IDIOMA = "languages=";
    private final String URL_NEXT = "&page=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibro> datosLibros = new ArrayList<>();

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    *************** MENÚ PRINCIPAL ***************
                    1 - Buscar libros en la API por título
                    2 - Buscar libros en la API por idioma
                    3 - Listar todos los libros registrados en la base de datos
                    4 - Listar todos los autores registrados en la base de datos
                    5 - Buscar libros en la base de datos por autor
                    6 - Buscar autores vivos por rango de años de nacimiento en la base de datos
                    7 - Ver el Top 10 de libros más descargados en la base de datos
                    8 - Agregar un nuevo libro manualmente a la base de datos
                    9 - Eliminar un libro o autor de la base de datos
                    0 - Salir del programa
                    ************************************************
                    """;
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        buscarLibroPorIdioma();
                        break;
                    case 3:
                        verLibrosRegistrados();
                        break;
                    case 4:
                        verAutoresRegistrados();
                        break;
//                    case 5:
//                        buscarLibroPorAutorEnBaseDeDatos();
//                        break;
//                    case 6:
//                        buscarAutoresVivosPorRangoDeAnos();
//                        break;
                    case 7:
                        top10LibrosMasDescargados();
                        break;
                    case 8:
                        agregarLibroManualmenteABaseDeDatos();
                        break;
                    case 9:
                        eliminarLibroOAutorDeBaseDeDatos();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }    catch (InputMismatchException e){
                    System.out.println("Opción inválida. Intente de nuevo.");
                    teclado.nextLine(); // Limpiar el buffer del scanner
            }
        }

    }

    private void buscarLibroPorTitulo() {
        System.out.println("Por favor escribe el nombre del libro que deseas buscar:");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + URL_SEARCH + nombreLibro.replace(" ", "%20"));
        var datos = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> primerLibro = datos.libros().stream().findFirst();
        if (primerLibro.isPresent()) {
            DatosLibro libro = primerLibro.get();

            // Mostrar detalles del libro usando toString
            System.out.println(libro.toString());

            datosLibros.add(libro);

            if (!libro.formatos().isEmpty()) {
                mostrarOpcionesDeDescarga(libro.formatos());
            } else {
                System.out.println("No hay enlaces de descarga disponibles para este libro.");
            }
        } else {
            System.out.println("No se encontraron libros con ese título.");
        }
    }

//    private void buscarLibroTitulo() {
//        System.out.println("Por favor escribe el nombre del libro que deseas buscar");
//        var nombreLibro = teclado.nextLine();
//        var json = consumoApi.obtenerDatos(URL_BASE + URL_SEARCH + nombreLibro.replace(" ", "%20"));
//        var datos = conversor.obtenerDatos(json, Datos.class);
//
//        // Obtener el primer libro coincidente
//        Optional<DatosLibro> primerLibro = datos.libros().stream().findFirst();
//        if (primerLibro.isPresent()) {
//            DatosLibro libro = primerLibro.get();
//            System.out.println("Título: " + libro.titulo());
//
//            // Mostrar autores
//            libro.autor().forEach(autor -> {
//                System.out.println("Autor: " + autor.nombre());
//                // System.out.println("Año de Nacimiento: " + autor.fechaDeNacimiento());
//                // System.out.println("Año de Muerte: " + autor.fechaDeMuerte());
//            });
//            System.out.println("---------------------------------------");
//            // Almacenar el libro en la lista de datosLibros
//            datosLibros.add(libro);
//
//            // Mostrar opciones de descarga si están disponibles
//            if (!libro.formatos().isEmpty()) {
//                mostrarOpcionesDeDescarga(libro.formatos());
//            } else {
//                System.out.println("No hay enlaces de descarga disponibles para este libro.");
//            }
//        } else {
//            System.out.println("No se encontraron libros con ese título.");
//        }
//    }

    private void buscarLibroPorIdioma(){
        System.out.println("Por favor selecciona el idioma del libro que deseas buscar:");
        var opcionesIdioma = """
            ***************
            1 - Español
            2 - Francés
            3 - Inglés
            4 - Portugués
            ***************
            """;
        System.out.println(opcionesIdioma);

        int opcionIdioma = Integer.parseInt(teclado.nextLine());
        String codigoIdioma;
        // Asignar el código de idioma en función de la selección del usuario
        switch (opcionIdioma) {
            case 1 -> codigoIdioma = "es";
            case 2 -> codigoIdioma = "fr";
            case 3 -> codigoIdioma = "en";
            case 4 -> codigoIdioma = "pt";
            default -> {
                System.out.println("Opción inválida, seleccionando español por defecto.");
                codigoIdioma = "es";
            }
        }
        int page = 1;
        boolean continuar = true;

        while (continuar) {
            var json = consumoApi.obtenerDatos(URL_BASE + URL_IDIOMA + codigoIdioma + URL_NEXT + page);
            var datos = conversor.obtenerDatos(json, Datos.class);

            // Verificar si hay libros en el idioma seleccionado y listarlos
            if (datos.libros().isEmpty()) {
                System.out.println("No se encontraron libros en el idioma seleccionado.");
                continuar = false;
            } else {
                System.out.println("Libros disponibles en el idioma seleccionado (Página " + page + "):");

                datos.libros().forEach(libro -> {
                    System.out.println(libro.toString());
                    // Agregar cada libro obtenido a la lista de datosLibros
                    datosLibros.add(libro);

                    // Mostrar opciones de descarga si están disponibles
                    if (!libro.formatos().isEmpty()) {
                        mostrarOpcionesDeDescarga(libro.formatos());
                    } else {
                        System.out.println("No hay enlaces de descarga disponibles para este libro.");
                    }
                    System.out.println("-------------------------------------------------------------------");
                });

                // Preguntar si el usuario desea continuar con la siguiente página
                System.out.println("¿Deseas ver más resultados? (S para continuar / cualquier otra tecla para salir + enter): ");
                String respuesta = teclado.nextLine().trim().toLowerCase();
                if (!respuesta.equals("s")) {
                    continuar = false;
                } else {
                    page++;
                }
            }
        }
    }

    private void verLibrosRegistrados() {
        if (datosLibros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Libros registrados en la lista:");
            datosLibros.forEach(libro -> {
                System.out.println(libro.toString());
                //libro.autor().forEach(autor -> System.out.println("Autor: " + autor.nombre()));

                // Mostrar opciones de descarga si están disponibles
                if (!libro.formatos().isEmpty()) {
                    mostrarOpcionesDeDescarga(libro.formatos());
                } else {
                    System.out.println("No hay enlaces de descarga disponibles para este libro.");
                }
                // Línea punteada para separar registros
                System.out.println("-------------------------------------------------------------------");
            });
        }
    }

    private void verAutoresRegistrados() {
        if (datosLibros.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("Autores registrados en la lista:");

            datosLibros.forEach(libro -> {
                // Obtener solo el primer autor del libro, si existe
                libro.autor().stream().findFirst().ifPresentOrElse(
                        autor -> {
                            // Imprimir solo el primer autor utilizando su método toString()
                            System.out.println(autor.toString());
                        },
                        // Caso en el que no hay autores
                        () -> System.out.println("Sin autor registrado")
                );

                // Línea punteada para separar registros
                System.out.println("-------------------------------------------------------------------");
            });
        }
    }
//
//    private void buscarLibroPorAutorEnBaseDeDatos() {
//        System.out.println("Por favor escribe el nombre del autor que deseas buscar");
//        var nombreAutor = teclado.nextLine();
//        var json = consumoApi.obtenerDatos(URL_BASE + URL_SEARCH + nombreAutor.replace(" ", "%20"));
//        var datos = conversor.obtenerDatos(json, Datos.class);
//
//        // Usando Streams para procesar y mostrar los libros y autores
//        datos.libros().stream()
//                .findFirst()
//                .ifPresentOrElse(libro -> {
//                            System.out.println("Primer resultado encontrado:");
//                            System.out.println("Título: " + libro.titulo());
//
//                            libro.autor().forEach(autor -> {
//                                System.out.println("Autor: " + autor.nombre());
//                                // System.out.println("Año de Nacimiento: " +
//                                //        (autor.fechaDeNacimiento() != null ? autor.fechaDeNacimiento() : "Desconocido"));
//                                // System.out.println("Año de Muerte: " +
//                                //        (autor.fechaDeMuerte() != null ? autor.fechaDeMuerte() : "Desconocido"));
//                            });
//
//                            // Almacena el libro en la lista de datosLibros
//                            datosLibros.add(libro);
//
//                            // Mostrar opciones de descarga si están disponibles
//                            if (!libro.formatos().isEmpty()) {
//                                mostrarOpcionesDeDescarga(libro.formatos());
//                            } else {
//                                System.out.println("No hay enlaces de descarga disponibles para este libro.");
//                            }
//                        },
//                        () -> System.out.println("No se encontraron libros para el autor especificado."));
//    }
//
//    private void buscarAutoresVivosPorRangoDeAnos() {
//        System.out.println("Por favor ingresa el año de inicio del rango en el que deseas encontrar autores vivos:");
//        int anoInicio = teclado.nextInt();
//        System.out.println("Por favor ingresa el año de fin del rango en el que deseas encontrar autores vivos:");
//        int anoFin = teclado.nextInt();
//        teclado.nextLine(); // Limpiar el buffer del scanner
//
//        int page = 1;
//        boolean continuar = true;
//
//        while (continuar) {
//            // Construir la URL de búsqueda con los parámetros de rango de años
//            String urlBusqueda = URL_BASE + "author_year_start=" + anoInicio + "&author_year_end=" + anoFin + URL_NEXT + page;
//            var json = consumoApi.obtenerDatos(urlBusqueda);
//            var datos = conversor.obtenerDatos(json, Datos.class);
//
//            if (datos.libros().isEmpty()) {
//                System.out.println("No se encontraron más libros con autores vivos en el rango especificado en la página " + page + ".");
//                continuar = false;
//            } else {
//                System.out.println("Libros con autores vivos entre los años " + anoInicio + " y " + anoFin + " (Página " + page + "):");
//
//                // Mostrar los libros y sus autores
//                datos.libros().forEach(libro -> {
//                    System.out.println("Título: " + libro.titulo());
//                    libro.autor().forEach(autor -> {
//                        System.out.println("Autor: " + autor.nombre());
//                        System.out.println("Año de Nacimiento: " +
//                                (autor.fechaDeNacimiento() != null ? autor.fechaDeNacimiento() : "Desconocido"));
//                        System.out.println("Año de Muerte: " +
//                                (autor.fechaDeMuerte() != null ? autor.fechaDeMuerte() : "Desconocido"));
//                        System.out.println("---------------------------------------");
//                    });
//                    // Agregar el libro a la lista de datosLibros si lo deseas
//                    datosLibros.add(libro);
//                });
//
//                // Preguntar si el usuario desea continuar con la siguiente página
//                System.out.println("¿Deseas ver más resultados? (S para continuar / cualquier otra tecla para salir): ");
//                String respuesta = teclado.nextLine().trim().toLowerCase();
//                if (!respuesta.equals("s")) {
//                    continuar = false;
//                } else {
//                    page++;
//                }
//            }
//        }
//    }

    private void top10LibrosMasDescargados() {

    }

    private void agregarLibroManualmenteABaseDeDatos() {

    }

    private void eliminarLibroOAutorDeBaseDeDatos() {

    }

    private void mostrarOpcionesDeDescarga(Map<String, String> enlacesDescarga) {
        //System.out.println("Opciones de descarga disponibles para este libro:");

        // Priorizar los formatos para lectura
        if (enlacesDescarga.containsKey("application/epub+zip")) {
            //System.out.println("Formato EPUB (compatible con la mayoría de lectores electrónicos):");
            System.out.println("Descargar aquí: " + enlacesDescarga.get("application/epub+zip"));
        } else if (enlacesDescarga.containsKey("application/x-mobipocket-ebook")) {
            //System.out.println("Formato MOBI (compatible con Kindle):");
            System.out.println("Descargar aquí: " + enlacesDescarga.get("application/x-mobipocket-ebook"));
        } else if (enlacesDescarga.containsKey("text/html")) {
            //System.out.println("Formato HTML (para lectura en navegador):");
            System.out.println("Leer aquí: " + enlacesDescarga.get("text/html"));
        } else if (enlacesDescarga.containsKey("text/plain; charset=us-ascii")) {
            //System.out.println("Formato Texto (ASCII):");
            System.out.println("Descargar aquí: " + enlacesDescarga.get("text/plain; charset=us-ascii"));
        } else {
            System.out.println("No se encontró un formato de lectura compatible disponible.");
        }
    }
}



