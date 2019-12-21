package Controller;

import Model.BaseDatos;
import Model.Coord;
import Model.Data;
import Model.Prediction;
import Services.IWeather;
import Services.OpenWeather;

import java.io.IOException;
import java.io.Serializable;
import java.net.ConnectException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Aplicacion implements Serializable {

    private IWeather servicio;
    private BaseDatos baseDatos;

    public Aplicacion() {
        servicio = new OpenWeather();
        baseDatos = new BaseDatos();
    }

    public Aplicacion(IWeather servicio) {
        this.servicio = servicio;
        this.baseDatos = new BaseDatos();
    }

    public Data getTiempoCiudad(String ciudad) throws IllegalArgumentException {
        Data tiempo = servicio.getTiempoCiudad(ciudad);
        return tiempo;
    }

    public Data getTiempoCoordenadas(double latitud, double longitud) throws IllegalArgumentException {
        Data tiempo = servicio.getTiempoCoordenadas(latitud, longitud);

        return tiempo;
    }

    public Prediction getPrediccionCiudad(String ciudad) throws IllegalArgumentException {
        Prediction prediccion = servicio.getPrediccionCiudad(ciudad);

        return prediccion;
    }

    public Prediction getPrediccionCoordenadas(double latitud, double longitud) throws IllegalArgumentException {
        Prediction prediccion = servicio.getPrediccionCoordenadas(latitud, longitud);

        return prediccion;
    }

    public void actualizarBaseDatos() {
        baseDatos.borrarSolicidudesViejas();
    }
    public void getTiempoCiudad() throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca una ciudad: ");
        String ciudad = sc.nextLine();
        System.out.println("¿Qué tipo de información quieres: ");
        System.out.println("1- Básica ");
        System.out.println("2- Detallada ");

        System.out.print("Elige una opción: ");
        int opcion = sc.nextInt();
        try {
            Data tiempo = servicio.getTiempoCiudad(ciudad);

            if (tiempo != null) {
                baseDatos.anadirTiempoCiudad(ciudad, tiempo);
                baseDatos.anadirFecha(ciudad, baseDatos.getFechasBusquedaCiudadBD());
                imprimirTiempo(opcion, tiempo);
            } else {
                System.out.println("No se ha encontrado una ciudad con el nombre: " + ciudad);
            }
        } catch (Exception e) {

            //Compruebo si la ciudad esta en cache, si no hay conexion
            if (baseDatos.getFechasBusquedaCiudadBD().containsKey(ciudad)) {
                int dias = baseDatos.getDiasHastaHoyBusquedaCiudad(ciudad);
                Data tiempo = baseDatos.getCiudadesActualBD().get(ciudad);
                System.out.println("Se ha perdido la conexion con la api:");
                System.out.println("Se ha podido recuperar datos de la ciudad " + ciudad + " de una solicitud de hace " + dias + " dias");
                imprimirTiempo(opcion, tiempo);
            } else {
                System.out.println("Se ha perdido la conexion con la api.");
            }

        }


    }

    public void getTiempoCoordenadas() throws IOException {

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una longitud: ");
        double longitud = Double.parseDouble(sc.next());

        System.out.print("Introduzca una latitud: ");
        double latitud = Double.parseDouble(sc.next());

        String coordenadas = Double.toString(latitud) + ", " + Double.toString(longitud);

        System.out.println("¿Qué tipo de información quieres: ");
        System.out.println("1- Básica ");
        System.out.println("2- Detallada ");

        System.out.print("Elige una opción: ");
        int opcion = sc.nextInt();
        try {
            Data tiempo = servicio.getTiempoCoordenadas(latitud, longitud);

            if (tiempo != null) {
                baseDatos.anadirTiempoCoordenadas(coordenadas, tiempo);
                baseDatos.anadirFecha(coordenadas, baseDatos.getFechasBusquedaCoordenadaDB());
                imprimirTiempo(opcion, tiempo);
            } else {
                System.out.println("No se ha encontrado una coordenada con el nombre: " + coordenadas);

            }
        } catch (Exception e) {
            if (baseDatos.getFechasBusquedaCoordenadaDB().containsKey(coordenadas)) {
                int dias = baseDatos.getDiasHastaHoyBusquedaCor(coordenadas);
                Data tiempo = baseDatos.getCoordenadasActualBD().get(coordenadas);
                System.out.println("Se ha perdido la conexion con la api:");
                System.out.println("Se ha podido recuperar datos de la coordenada " + coordenadas + " de una solicitud de hace " + dias + " dias");
                imprimirTiempo(opcion, tiempo);
            } else {
                System.out.println("Se ha perdido la conexion con la api.");
            }
        }

    }


    public void imprimirTiempo(int opcion, Data tiempo) {
        if (opcion == 1) {
            System.out.println(tiempo.informacionBasica());

        } else if (opcion == 2) {
            System.out.println(tiempo.informacionDetallada());
        }
    }


    public void getPrediccionCiudad() throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca una ciudad: ");
        String ciudad = sc.nextLine();
        try {
            Prediction prediccion = servicio.getPrediccionCiudad(ciudad);

            if (prediccion != null) {
                baseDatos.anadirPrediccionCiudad(ciudad, prediccion);
                baseDatos.anadirFecha(ciudad, baseDatos.getFechasPrediccionCiudadBD());
                System.out.println("Weather prediccion " + prediccion.getInformacion());
            } else {
                System.out.println("No se ha encontrado una ciudad con el nombre: " + ciudad);
            }

        } catch (Exception e) {

            if (baseDatos.getFechasPrediccionCiudadBD().containsKey(ciudad)) {
                int dias = baseDatos.getDiasHastaHoyPrediccionCiudad(ciudad);
                Prediction prediccion = baseDatos.getCiudadesPrediccionBD().get(ciudad);
                System.out.println("Se ha perdido la conexion con la api:");
                System.out.println("Se ha podido recuperar datos de la ciudad " + ciudad + " de una solicitud de hace " + dias + " dias");
                System.out.println("Weather prediccion " + prediccion.getInformacion());
            } else {
                System.out.println("Se ha perdido la conexion con la api.");
            }

        }


    }

    public void getPrediccionCoordenadas() throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca una longitud: ");
        double longitud = Double.parseDouble(sc.next());

        System.out.print("Introduzca una latitud: ");
        double latitud = Double.parseDouble(sc.next());

        String coordenadas = Double.toString(latitud) + ", " + Double.toString(longitud);
        try {
            Prediction prediccion = servicio.getPrediccionCoordenadas(latitud, longitud);
            if (prediccion != null) {
                baseDatos.anadirPrediccionCoordenadas(coordenadas, prediccion);
                baseDatos.anadirFecha(coordenadas, baseDatos.getFechasPrediccionCoordenadaDB());
                System.out.println("Weather prediccion " + prediccion.toString());
            } else {
                System.out.println("No se ha encontrado una coordenada con el nombre: " + coordenadas);
            }
        } catch (Exception e) {
            if (baseDatos.getFechasPrediccionCoordenadaDB().containsKey(coordenadas)) {
                int dias = baseDatos.getDiasHastaHoyPrediccionCor(coordenadas);
                Prediction prediccion = baseDatos.getCoordenadasPrediciconBD().get(coordenadas);
                System.out.println("Se ha perdido la conexion con la api:");
                System.out.println("Se ha podido recuperar datos de la coordenada " + coordenadas + " de una solicitud de hace " + dias + " dias");
                System.out.println("Weather prediccion " + prediccion.toString());
            } else {
                System.out.println("Se ha perdido la conexion con la api.");
            }
        }
    }


    //SECCIÓN DE FAVORITOS
    public void getFavoritos(){
       TreeSet<String> ciudades =  baseDatos.getCiudadesFavoritas();


        System.out.println("---------------CIUDADES---------------");


        if(ciudades.isEmpty()){
           System.out.println("No tienes ciudades favoritas");
        }else{
           for(String ciudad : ciudades){
               System.out.println(ciudad);
           }
        }

        System.out.println("");


        TreeSet<String> coordenadas =  baseDatos.getCoordenadasFavoritas();


        System.out.println("---------------COORDENADAS---------------");


        if(coordenadas.isEmpty()){
            System.out.println("No tienes coordenadas favoritas");
        }else{
            for(String coordenada : coordenadas){
                System.out.println(coordenada);
            }
        }

        System.out.println("");

    }

    public void anadirCiudadFavorita() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca una ciudad: ");
        String ciudad = sc.nextLine();

        if (baseDatos.getCiudadesFavoritas().contains(ciudad)) {
            System.out.println(ciudad + " ya existe en favoritos");
        } else {

            boolean anadir = baseDatos.anadirCiudadFavorita(ciudad);

            if (anadir) {
                System.out.println("Se ha añadido " + ciudad + " a favoritos");
            } else {
                System.out.println("ERRROR. No se ha podidio añadir " + ciudad + " a favoritos");
            }
        }
    }

    public void borrarCiudadFavorita(){

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una ciudad: ");
        String ciudad=sc.nextLine();

        if(baseDatos.getCiudadesFavoritas().contains(ciudad)){

            boolean borrar =baseDatos.borrarCiudadFavorita(ciudad);

            if(borrar){
                System.out.println("Se ha borrado "+ciudad+" de favoritos");
            }else {
                System.out.println("ERRROR. No se ha podidio borrar "+ciudad+" de favoritos");
            }

        }else{

            System.out.println(ciudad+" no existe en favoritos");

        }
    }

    public void anadirCoordenadaFavorita() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduzca una latitud: ");
        String latitud = sc.next();

        System.out.print("Introduzca una longitud: ");
        String longitud = sc.next();

        String coordenadas = latitud + ", " + longitud;

        if (baseDatos.getCoordenadasFavoritas().contains(coordenadas)) {
            System.out.println(coordenadas + " ya existe en favoritos");
        } else {

            boolean anadir = baseDatos.anadirCoordenadasFavoritas(coordenadas);

            if (anadir) {
                System.out.println("Se ha añadido " + coordenadas + " a favoritos");
            } else {
                System.out.println("ERRROR. No se ha podidio añadir " + coordenadas + " a favoritos");
            }
        }
    }

    public void borrarCoordenadaFavorita(){

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una latitud: ");
        String latitud=sc.next();

        System.out.print("Introduzca una longitud: ");
        String longitud=sc.next();

        String coordenadas = latitud+", "+longitud;

        if(baseDatos.getCoordenadasFavoritas().contains(coordenadas)){

            boolean borrar =baseDatos.borrarCoordenadasFavoritas(coordenadas);

            if(borrar){
                System.out.println("Se ha borrado "+coordenadas+" de favoritos");
            }else {
                System.out.println("ERRROR. No se ha podidio borrar "+coordenadas+" de favoritos");
            }

        }else{

            System.out.println(coordenadas+" no existe en favoritos");

        }
    }

    public void getTiempoCiudadesFavoritas() throws IOException {

        TreeSet<String> favoritos =  baseDatos.getCiudadesFavoritas();


        if(!favoritos.isEmpty()){
            for(String lugar : favoritos){
                System.out.println(lugar);
                System.out.println(servicio.getTiempoCiudad(lugar).informacionBasica());


            }
        }else{
            System.out.println("No tienes lugares favoritos");

        }

    }

    public void getPrediccionCiudadesFavoritas() throws IOException {

        TreeSet<String> favoritos =  baseDatos.getCiudadesFavoritas();


        if(!favoritos.isEmpty()){
            for(String lugar : favoritos){
                System.out.println(lugar);
                System.out.println(servicio.getPrediccionCiudad(lugar).getInformacion());

            }
        }else{
            System.out.println("No tienes lugares favoritos");

        }

    }


    public void getTiempoCoordenadasFavoritas() throws IOException {

        TreeSet<String> favoritos =  baseDatos.getCoordenadasFavoritas();


        if(!favoritos.isEmpty()){
            for(String lugar : favoritos){
                String [] vector = lugar.split(",");
                System.out.println(lugar);
                System.out.println(servicio.getTiempoCoordenadas(Double.parseDouble(vector[0]), Double.parseDouble(vector[1])).informacionBasica());

            }
        }else{
            System.out.println("No tienes lugares favoritos");

        }

    }

    public void getPrediccionCoordenadasFavoritas() throws IOException {

        TreeSet<String> favoritos =  baseDatos.getCoordenadasFavoritas();


        if(!favoritos.isEmpty()){
            for(String lugar : favoritos){
                String [] vector = lugar.split(",");
                System.out.println(lugar);
                System.out.println(servicio.getPrediccionCoordenadas(Double.parseDouble(vector[0]), Double.parseDouble(vector[1])).getInformacion());

            }
        }else{
            System.out.println("No tienes lugares favoritos");

        }

    }

    public void addEtiquetaCoordenadas(String etiqueta, Coord coord) {
        if (verificarCoordenadas(coord))
            baseDatos.addEtiquetaCoordenadas(etiqueta, coord);
        else
            throw new IllegalArgumentException();
    }

    public Coord getEtiqueta(String etiqueta) {
        return baseDatos.getEtiquetaCoordenadas(etiqueta);
    }

    private boolean verificarCoordenadas(Coord coord) {

        if (-180 <= coord.getLon() && coord.getLon() <= 180 && -90 <= coord.getLat() && coord.getLat() <= 90)
            return true;
        return false;
    }
}
