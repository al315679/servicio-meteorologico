package Controller;

import Model.BaseDatos;
import Model.Coord;
import Model.Data;
import Model.Prediction;
import Services.IWeather;
import Services.OpenWeather;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.TreeSet;

public class Aplicacion implements Serializable {

    private IWeather servicio;
    private BaseDatos baseDatos;


    public Aplicacion(){
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

    public void getTiempoCiudad() throws IOException {

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una ciudad: ");
        String ciudad=sc.nextLine();

        System.out.println("¿Qué tipo de información quieres: ");
        System.out.println("1- Básica ");
        System.out.println("2- Detallada ");

        System.out.print("Elige una opción: ");
        int opcion = sc.nextInt();

        Data tiempo = servicio.getTiempoCiudad(ciudad);

        if(tiempo != null) {
            baseDatos.añadirTiempoCiudad(ciudad, tiempo);
            if(opcion == 1){
                System.out.println(tiempo.informacionBasica());

            }else if(opcion == 2){
                System.out.println(tiempo.informacionDetallada());

            }
        }

    }

    public void getTiempoCoordenadas() throws IOException {

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una longitud: ");
        double longitud = Double.parseDouble(sc.next());

        System.out.print("Introduzca una latitud: ");
        double latitud = Double.parseDouble(sc.next());

        String coordenadas = Double.toString(latitud)+", "+Double.toString(longitud);

        System.out.println("¿Qué tipo de información quieres: ");
        System.out.println("1- Básica ");
        System.out.println("2- Detallada ");

        System.out.print("Elige una opción: ");
        int opcion = sc.nextInt();

        Data tiempo = servicio.getTiempoCoordenadas(latitud, longitud);

        if(tiempo != null){
            baseDatos.añadirTiempoCoordenadas(coordenadas, tiempo);
            if(opcion == 1){
                System.out.println(tiempo.informacionBasica());

            }else if(opcion == 2){
                System.out.println(tiempo.informacionDetallada());
            }
        }

    }


    public void getPrediccionCiudad() throws IOException {

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una ciudad: ");
        String ciudad=sc.nextLine();

        Prediction prediccion = servicio.getPrediccionCiudad(ciudad);

        if(prediccion != null){
            baseDatos.añadirPrediccionCiudad(ciudad, prediccion);
            System.out.println("Weather prediccion " + prediccion.getInformacion());
        }



    }

    public void getPrediccionCoordenadas() throws IOException {

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una longitud: ");
        double longitud = Double.parseDouble(sc.next());

        System.out.print("Introduzca una latitud: ");
        double latitud = Double.parseDouble(sc.next());

        String coordenadas = Double.toString(latitud)+", "+Double.toString(longitud);

        Prediction prediccion = servicio.getPrediccionCoordenadas(latitud, longitud);
        baseDatos.añadirPrediccionCoordenadas(coordenadas, prediccion);


        System.out.println("Weather prediccion " + prediccion.toString());

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

    public void añadirCiudadFavorita(){

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una ciudad: ");
        String ciudad=sc.nextLine();

        if(baseDatos.getCiudadesFavoritas().contains(ciudad)){
            System.out.println(ciudad+" ya existe en favoritos");
        }else{

            boolean añadir =baseDatos.añadirCiudadFavorita(ciudad);

            if(añadir){
                System.out.println("Se ha añadido "+ciudad+" a favoritos");
            }else {
                System.out.println("ERRROR. No se ha podidio añadir "+ciudad+" a favoritos");
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

    public void añadirCoordenadaFavorita(){

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una latitud: ");
        String latitud=sc.next();

        System.out.print("Introduzca una longitud: ");
        String longitud=sc.next();

        String coordenadas = latitud+", "+longitud;

        if(baseDatos.getCoordenadasFavoritas().contains(coordenadas)){
            System.out.println(coordenadas+" ya existe en favoritos");
        }else{

            boolean añadir =baseDatos.añadirCoordenadasFavoritas(coordenadas);

            if(añadir){
                System.out.println("Se ha añadido "+coordenadas+" a favoritos");
            }else {
                System.out.println("ERRROR. No se ha podidio añadir "+coordenadas+" a favoritos");
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
        throw new UnsupportedOperationException("Metodo no implementado");
    }

    public Coord getEtiqueta(String etiqueta) {
        throw new UnsupportedOperationException("Metodo no implementado");
    }
}
