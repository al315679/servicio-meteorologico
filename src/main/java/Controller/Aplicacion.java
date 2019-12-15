package Controller;

import Model.BaseDatos;
import Model.Data;
import Model.Prediction;
import Services.OpenWeather;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.TreeSet;

public class Aplicacion implements Serializable {

    private OpenWeather servicio;
    private BaseDatos baseDatos;


    public Aplicacion(){
        servicio = new OpenWeather();
        baseDatos = new BaseDatos();
    }

    public void getTiempoCiudad() throws IOException {

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una ciudad: ");
        String ciudad=sc.next();

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
        String longitud=sc.next();

        System.out.print("Introduzca una latitud: ");
        String latitud=sc.next();

        String coordenadas = latitud+", "+longitud;

        System.out.println("¿Qué tipo de información quieres: ");
        System.out.println("1- Básica ");
        System.out.println("2- Detallada ");

        System.out.print("Elige una opción: ");
        int opcion = sc.nextInt();

        Data tiempo = servicio.getTiempoCoordenadas();

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
        String ciudad=sc.next();

        Prediction prediccion = servicio.getPrediccionCiudad(ciudad);

        if(prediccion != null){
            baseDatos.añadirPrediccionCiudad(ciudad, prediccion);
            System.out.println("Weather prediccion " + prediccion.toString());
        }



    }

    public void getPrediccionCoordenadas() throws IOException {

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una longitud: ");
        String longitud=sc.next();

        System.out.print("Introduzca una latitud: ");
        String latitud=sc.next();

        String coordenadas = latitud+", "+longitud;

        Prediction prediccion = servicio.getPrediccionCoordenadas();
        baseDatos.añadirPrediccionCoordenadas(coordenadas, prediccion);


        System.out.println("Weather prediccion " + prediccion.toString());

    }

    public void getFavoritos(){
       TreeSet<String> favoritos =  baseDatos.getCiudadesFavoritas();

       if(favoritos.isEmpty()){
           System.out.println("No tienes lugares favoritos");
       }else{
           for(String lugar : favoritos){
               System.out.println(lugar);
           }
       }
    }

    public void añadirFavorito(){

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una ciudad: ");
        String ciudad=sc.next();

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

    public void borrarFavorito(){

        Scanner sc=new Scanner(System.in);

        System.out.print("Introduzca una ciudad: ");
        String ciudad=sc.next();

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

    public void getTiempoCiudadesFavoritas() throws IOException {

        TreeSet<String> favoritos =  baseDatos.getCiudadesFavoritas();


        if(!favoritos.isEmpty()){
            for(String lugar : favoritos){
                System.out.println(lugar);
                servicio.getTiempoCiudad(lugar);
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
                servicio.getPrediccionCiudad(lugar);
            }
        }else{
            System.out.println("No tienes lugares favoritos");

        }

    }

}
