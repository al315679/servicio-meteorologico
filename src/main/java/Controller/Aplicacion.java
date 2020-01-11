package Controller;

import Model.BaseDatos;
import Model.Coord;
import Model.Data;
import Model.Prediction;
import Services.IWeather;
import Services.OpenWeather;
import View.InterfaceVista;
import View.Vista;
import View.VistaFavoritos;

import java.io.IOException;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Aplicacion implements Serializable {

    private IWeather servicio;
    private BaseDatos baseDatos;

    @SuppressWarnings("unused")
    private InterfaceVista vista;

    public Aplicacion() {
        servicio = new OpenWeather();
        baseDatos = new BaseDatos();
    }

    public Aplicacion(IWeather servicio) {
        this.servicio = servicio;
        this.baseDatos = new BaseDatos();
    }
    public void setServicio(IWeather servicio) { this.servicio = servicio; }

    public void setModelo(BaseDatos baseDatos) {
        this.baseDatos = baseDatos;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
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

    public String getTiempoCiudadVista(String ciudad, String tipo){
        Data tiempo = null;
        Data tiempoGuardado = null;
        int horas=0;
        String res ="";
        if (baseDatos.getCiudadesActualBD().containsKey(ciudad)) {
            tiempoGuardado = baseDatos.getCiudadesActualBD().get(ciudad);
            horas = baseDatos.getDiasHastaHoyBusquedaCiudad(ciudad, true);
            if (horas <= 1) {
                if (horas == 0) {
                    res += "Este estado de tiempo se busco hace menos de una hora" + '\n';
                }
                else {
                    res += "Este estado de tiempo se busco hace " + horas + " horas" + '\n';
                }
                res += getInfoTiempo( tipo, tiempoGuardado);
                return res;
            }
        }

        try {
            tiempo = getTiempoCiudad(ciudad);
        }
        catch (IllegalArgumentException e){
            return "No se ha encontrado una ciudad con el nombre "+ ciudad;
        }

        if (tiempo != null) {
            baseDatos.anadirTiempoCiudad(ciudad.toLowerCase(), tiempo);
            baseDatos.anadirFecha(ciudad.toLowerCase(), baseDatos.getFechasBusquedaCiudadBD());
            return getInfoTiempo(tipo, tiempo);

        }
        res+="No hay conexion con el api"+'\n';
        if (tiempoGuardado!= null) {
            res += "Este estado de tiempo se busco hace " + horas + " horas" + '\n';
            res += getInfoTiempo (tipo, tiempoGuardado);
        }
        return res;
        /*
        String resultado="";
        try {
            Data tiempo = servicio.getTiempoCiudad(ciudad);

            if (tiempo != null) {
                baseDatos.anadirTiempoCiudad(ciudad, tiempo);
                baseDatos.anadirFecha(ciudad, baseDatos.getFechasBusquedaCiudadBD());
                resultado += getInfoTiempo(tipo, tiempo);
            } else {
                //Compruebo si la ciudad esta en cache, si no hay conexion
                if (baseDatos.getFechasBusquedaCiudadBD().containsKey(ciudad)) {
                    int horas = baseDatos.getDiasHastaHoyBusquedaCiudad(ciudad, true);
                    tiempo = baseDatos.getCiudadesActualBD().get(ciudad);
                    resultado+="Se ha perdido la conexion con la api:"+'\n';
                    resultado+="Se ha podido recuperar datos de la ciudad " + ciudad + " de una solicitud de hace " + horas + " horas"+'\n';
                    resultado += getInfoTiempo(tipo, tiempo);
                } else {
                    resultado="Se ha perdido la conexion con la api.";
                }

            }
        } catch (IllegalArgumentException e) {
            return "No se ha encontrado una ciudad con el nombre: " + ciudad;
        }
        return resultado;
        */
    }

    private String getPrediccionCiudadVista(String ciudad) {
        Prediction prediccion = null;
        Prediction prediccionGuardada = null;
        int horas=0;
        String res ="";
        if (baseDatos.getCiudadesPrediccionBD().containsKey(ciudad)) {
            prediccionGuardada = baseDatos.getCiudadesPrediccionBD().get(ciudad);
            horas = baseDatos.getDiasHastaHoyBusquedaCiudad(ciudad, true);
            if (horas <= 1) {
                if (horas == 0)
                    res +="Esta prediccion del tiempo se busco hace menos de una hora"+'\n';
                else
                    res +="Esta prediccion del tiempo se busco hace "+ horas+ " horas"+'\n';
                res += prediccionGuardada.getInformacion();
                return res;
            }
        }

        try {
            prediccion = getPrediccionCiudad(ciudad);
        }

        catch (IllegalArgumentException e){
            return "No se ha encontrado una ciudad con el nombre"+ ciudad;
        }

        if (prediccion != null) {
            baseDatos.anadirPrediccionCiudad(ciudad.toLowerCase(), prediccion);
            baseDatos.anadirFecha(ciudad.toLowerCase(), baseDatos.getFechasBusquedaCiudadBD());
            return prediccion.getInformacion();

        }
        res+="No hay conexion con el api";
        if (prediccionGuardada!= null) {
            res += "Este estado de tiempo se busco hace " + horas + " horas" + '\n';
            res += prediccionGuardada.getInformacion ( );
        }
        return res;
        /*
        String resultado="";
        try {
            Prediction prediccion = servicio.getPrediccionCiudad(ciudadCoordenada);

            if (prediccion != null) {
                baseDatos.anadirPrediccionCiudad(ciudadCoordenada, prediccion);
                baseDatos.anadirFecha(ciudadCoordenada, baseDatos.getFechasPrediccionCiudadBD());
                resultado="Weather prediccion " + prediccion.getInformacion();
            } else {
                if (baseDatos.getFechasPrediccionCiudadBD().containsKey(ciudadCoordenada)) {
                    int horas = baseDatos.getDiasHastaHoyPrediccionCiudad(ciudadCoordenada,true);
                    prediccion = baseDatos.getCiudadesPrediccionBD().get(ciudadCoordenada);
                    resultado+="Se ha perdido la conexion con la api:";
                    resultado+="Se ha podido recuperar datos de la ciudad " + ciudadCoordenada + " de una solicitud de hace " + horas + " horas";
                    resultado+="Weather prediccion " + prediccion.getInformacion();
                } else {
                    resultado="Se ha perdido la conexion con la api.";
                }
            }

        } catch (IllegalArgumentException e) {
            return "No se ha encontrado una ciudad con el nombre: " + ciudadCoordenada;
        }
        return resultado;

         */
    }

    private String getPrediccionCoordenadasVista(float latitud, float longitud) {

        String coordenadas = latitud + ", " +longitud;

        Prediction prediccion = null;
        Prediction prediccionGuardada = null;
        int horas=0;
        String res ="";
        if (baseDatos.getCoordenadasPrediciconBD().containsKey(coordenadas)) {
            prediccionGuardada = baseDatos.getCoordenadasPrediciconBD().get(coordenadas);
            horas = baseDatos.getDiasHastaHoyPrediccionCor(coordenadas, true);
            if (horas <= 1) {
                if (horas == 0)
                    res +="Esta prediccion del tiempo se busco hace menos de una hora"+'\n';
                else
                    res +="Esta prediccion del tiempo se busco hace "+ horas+ " horas"+'\n';
                res += prediccionGuardada.getInformacion();
                return res;
            }
        }

        try {
            prediccion = getPrediccionCoordenadas(latitud, longitud);
        }
        catch (IllegalArgumentException e){
            return "No se ha encontrado una coordenada con estos parametros "+ coordenadas;
        }

        if (prediccion != null) {
            baseDatos.anadirPrediccionCoordenadas(coordenadas, prediccion);
            baseDatos.anadirFecha(coordenadas, baseDatos.getFechasPrediccionCoordenadaDB());
            return prediccion.getInformacion();

        }
        res+="No hay conexion con el api";
        if (prediccionGuardada!= null) {
            res += "Esta prediccion del tiempo se busco hace " + horas + " horas" + '\n';
            res += prediccionGuardada.getInformacion ( );
        }
        return res;
        /*
        try {
            Prediction prediccion = servicio.getPrediccionCoordenadas(latitud, longitud);
            if (prediccion != null) {
                baseDatos.anadirPrediccionCoordenadas(coordenadas, prediccion);
                baseDatos.anadirFecha(coordenadas, baseDatos.getFechasPrediccionCoordenadaDB());
                resultado="Weather prediccion " + prediccion.toString();
            } else {
                if (baseDatos.getFechasPrediccionCoordenadaDB().containsKey(coordenadas)) {
                    int horas = baseDatos.getDiasHastaHoyPrediccionCor(coordenadas, true);
                    prediccion = baseDatos.getCoordenadasPrediciconBD().get(coordenadas);
                    resultado += "Se ha perdido la conexion con la api:";
                    resultado += "Se ha podido recuperar datos de la coordenada " + coordenadas + " de una solicitud de hace " + horas + " horas";
                    resultado += "Weather prediccion " + prediccion.getInformacion();
                } else {
                    resultado = "Se ha perdido la conexion con la api.";
                }
            }
        } catch (Exception e) {
            resultado = "No se ha encontrado una coordenada con el nombre: " + coordenadas;

        }
        return resultado;


         */
    }

    public String getTiempoCoordenadasVista(float latitud, float longitud, String tipo) {
        String coordenadas = latitud + ", " + longitud;

        Data tiempo = null;
        Data tiempoGuardada = null;
        int horas=0;
        String res ="";
        if (baseDatos.getCoordenadasActualBD().containsKey(coordenadas)) {
            tiempoGuardada = baseDatos.getCoordenadasActualBD().get(coordenadas);
            horas = baseDatos.getDiasHastaHoyBusquedaCor(coordenadas, true);
            if (horas <= 1) {
                if (horas == 0)
                    res +="Este estado de tiempo se busco hace menos de una hora"+'\n';
                else
                    res +="Este estado de tiempo se busco hace "+ horas+ " horas"+'\n';
                res += getInfoTiempo(tipo, tiempoGuardada);
                return res;
            }
        }

        try {
            tiempo = getTiempoCoordenadas(latitud, longitud);
        }
        catch (IllegalArgumentException e){
            return "No se ha encontrado una coordenada con estos parametros "+ coordenadas;
        }

        if (tiempo != null) {
            baseDatos.anadirTiempoCoordenadas(coordenadas, tiempo);
            baseDatos.anadirFecha(coordenadas, baseDatos.getFechasBusquedaCoordenadaDB());
            return getInfoTiempo(tipo,tiempo);

        }
        res+="No hay conexion con el api";
        if (tiempoGuardada!= null) {
            res += "Este tiempo se busco hace " + horas + " horas" + '\n';
            res += getInfoTiempo (tipo, tiempoGuardada);
        }
        return res;
        /*
        try {
            Data tiempo = servicio.getTiempoCoordenadas(latitud, longitud);

            if (tiempo != null) {
                baseDatos.anadirTiempoCoordenadas(coordenadas, tiempo);
                baseDatos.anadirFecha(coordenadas, baseDatos.getFechasBusquedaCoordenadaDB());
                resultado = getInfoTiempo(tipo, tiempo);
            } else {
                if (baseDatos.getFechasBusquedaCoordenadaDB().containsKey(coordenadas)) {
                    int horas = baseDatos.getDiasHastaHoyBusquedaCor(coordenadas, true);
                    tiempo = baseDatos.getCoordenadasActualBD().get(coordenadas);
                    resultado += "Se ha perdido la conexion con la api:";
                    resultado += "Se ha podido recuperar datos de la coordenada " + coordenadas + " de una solicitud de hace " + horas + " horas";
                    resultado += getInfoTiempo(tipo, tiempo);
                } else {
                    resultado = "Se ha perdido la conexion con la api.";
                }


            }
        } catch (IllegalArgumentException e) {
            resultado = "No se ha encontrado una coordenada con el nombre: " + coordenadas;
        }
        return resultado;

         */
    }



    public void anadirFavoritos(VistaFavoritos vista) {

        String opcion = vista.getTipoBusqueda();

        String favorito = vista.getTexto();

        if (opcion.compareTo("Ciudad") == 0) {

            if (baseDatos.getCiudadesFavoritas().contains(favorito)) {
                System.out.println(favorito + " ya existe en favoritos");
                vista.AnadirExistente(favorito);
            } else {

                try {
                    Data ciudad = servicio.getTiempoCiudad(favorito);


                    boolean anadir = baseDatos.anadirCiudadFavorita(favorito);

                    if (anadir) {
                        System.out.println("Se ha añadido " + favorito + " a favoritos");
                        vista.AnadidoCorrectamente(favorito);
                        getFavoritos(vista);
                        getTiempoFavoritos(vista);
                        getPrediccionFavoritos(vista);
                    } else {
                        System.out.println("ERRROR. No se ha podidio añadir " + favorito + " a favoritos");
                        vista.AnadirError(favorito);
                    }

                }catch(IllegalArgumentException e){
                    vista.CiudadNoExiste(favorito);
                }

            }

        } else {

            if (opcion.compareTo("Coordenada") == 0) {

                if (baseDatos.getCoordenadasFavoritas().contains(favorito)) {
                    System.out.println(favorito + " ya existe en favoritos");
                    vista.AnadirExistente(favorito);
                } else {

                    try {

                        String coor [] = favorito.split(",");

                        Data ciudad = servicio.getTiempoCoordenadas(Double.parseDouble(coor[0]), Double.parseDouble(coor[1]));


                        boolean anadir = baseDatos.anadirCoordenadasFavoritas(favorito);

                        if (anadir) {
                            System.out.println("Se ha añadido " + favorito + " a favoritos");
                            vista.AnadidoCorrectamente(favorito);
                            getFavoritos(vista);
                            getTiempoFavoritos(vista);
                            getPrediccionFavoritos(vista);
                        } else {
                            System.out.println("ERRROR. No se ha podidio añadir " + favorito + " a favoritos");
                            vista.AnadirError(favorito);
                        }
                    }catch (IllegalArgumentException e){
                        vista.CoordenadasNoExiste(favorito);
                    }catch (ArrayIndexOutOfBoundsException e) {
                        vista.formatoCoordenadasIncorrecto();
                    }
                }
            }

        }

    }

    public void borrarFavoritos(VistaFavoritos vista){

        String opcion = vista.getTipoBusqueda();

        String favorito = vista.getTexto();

        if(opcion.compareTo("Ciudad") == 0 ){

            if(baseDatos.getCiudadesFavoritas().contains(favorito)){

                boolean borrar =baseDatos.borrarCiudadFavorita(favorito);

                if(borrar){
                    System.out.println("Se ha borrado "+favorito+" de favoritos");
                    vista.BorradoCorrectamente(favorito);
                    getFavoritos(vista);
                    getTiempoFavoritos(vista);
                    getPrediccionFavoritos(vista);
                }else {
                    System.out.println("ERRROR. No se ha podidio borrar "+favorito+" de favoritos");
                    vista.BorrarError(favorito);
                }

            }else{

                System.out.println(favorito+" no existe en favoritos");
                vista.BorrarNoExistente(favorito);

            }

        }else{

            if(baseDatos.getCoordenadasFavoritas().contains(favorito)){

                boolean borrar =baseDatos.borrarCoordenadasFavoritas(favorito);

                if(borrar){
                    System.out.println("Se ha borrado "+favorito+" de favoritos");
                    vista.BorradoCorrectamente(favorito);
                    getFavoritos(vista);
                    getTiempoFavoritos(vista);
                    getPrediccionFavoritos(vista);

                }else {
                    System.out.println("ERRROR. No se ha podidio borrar "+favorito+" de favoritos");
                    vista.BorrarError(favorito);
                }

            }else{

                System.out.println(favorito+" no existe en favoritos");
                vista.BorrarNoExistente(favorito);
            }
        }

    }

    public void getFavoritos(VistaFavoritos vista){

        TreeSet<String> ciudades =  baseDatos.getCiudadesFavoritas();


        System.out.println("---------------CIUDADES---------------");

        final StringBuilder sb = new StringBuilder();

        sb.append("---------------CIUDADES-------------------").append('\n');



        if(ciudades.isEmpty()){
            System.out.println("No tienes ciudades favoritas");
            sb.append("No tienes ciudades favoritas").append('\n').append('\n');
        }else{
            for(String ciudad : ciudades){
                System.out.println(ciudad);
                sb.append(ciudad).append('\n');
            }
        }

        System.out.println("");

        TreeSet<String> coordenadas =  baseDatos.getCoordenadasFavoritas();


        System.out.println("---------------COORDENADAS---------------");

        sb.append('\n').append("---------------COORDENADAS---------------").append('\n');

        if(coordenadas.isEmpty()){
            System.out.println("No tienes coordenadas favoritas");
            sb.append("No tienes coordenadas favoritas").append('\n').append('\n');
        }else{
            for(String coordenada : coordenadas){
                System.out.println(coordenada);
                sb.append(coordenada).append('\n');

            }
        }

        vista.setJlListaFavoritos(sb.toString());
        System.out.println("");

    }


    public void getTiempoFavoritos(VistaFavoritos vista){

        TreeSet<String> favoritos =  baseDatos.getCiudadesFavoritas();

        final StringBuilder sb = new StringBuilder();

        sb.append("---------------CIUDADES-------------------").append('\n');


        if(!favoritos.isEmpty()){
            for(String lugar : favoritos){
                System.out.println(lugar);
                System.out.println(servicio.getTiempoCiudad(lugar).informacionBasica());
                sb.append(servicio.getTiempoCiudad(lugar).informacionBasica()).append('\n');

            }
        }else{
            System.out.println("No tienes lugares favoritos");
            sb.append("No tienes ciudades favoritas").append('\n').append('\n');

        }

        TreeSet<String> favoritas =  baseDatos.getCoordenadasFavoritas();

        sb.append('\n').append("---------------COORDENADAS---------------").append('\n');

        if(!favoritas.isEmpty()){
            for(String lugar : favoritas){
                String [] vector = lugar.split(",");
                System.out.println(lugar);
                System.out.println(servicio.getTiempoCoordenadas(Double.parseDouble(vector[0]), Double.parseDouble(vector[1])).informacionBasica());
                sb.append(servicio.getTiempoCoordenadas(Double.parseDouble(vector[0]), Double.parseDouble(vector[1])).informacionBasica()).append('\n');

            }
        }else{
            System.out.println("No tienes lugares favoritos");
            sb.append("No tienes coordenadas favoritas").append('\n').append('\n');

        }

        vista.setJlActualFavoritos(sb.toString());

    }

    public void getPrediccionFavoritos(VistaFavoritos vista){

        TreeSet<String> favoritos =  baseDatos.getCiudadesFavoritas();

        final StringBuilder sb = new StringBuilder();

        sb.append("---------------CIUDADES-------------------").append('\n');


        if(!favoritos.isEmpty()){
            for(String lugar : favoritos){
                System.out.println(lugar);
                System.out.println(servicio.getPrediccionCiudad(lugar).getInformacion());
                sb.append(servicio.getPrediccionCiudad(lugar).getInformacion()).append('\n');


            }
        }else{
            System.out.println("No tienes lugares favoritos");
            sb.append("No tienes ciudades favoritas").append('\n').append('\n');


        }

        sb.append('\n').append("---------------COORDENADAS---------------").append('\n');


        TreeSet<String> favoritas =  baseDatos.getCoordenadasFavoritas();


        if(!favoritas.isEmpty()){
            for(String lugar : favoritas){
                String [] vector = lugar.split(",");
                System.out.println(lugar);
                System.out.println(servicio.getPrediccionCoordenadas(Double.parseDouble(vector[0]), Double.parseDouble(vector[1])).getInformacion());
                sb.append(servicio.getPrediccionCoordenadas(Double.parseDouble(vector[0]), Double.parseDouble(vector[1])).getInformacion()).append('\n');

            }
        }else{
            System.out.println("No tienes lugares favoritos");
            sb.append("No tienes coordenadas favoritas").append('\n').append('\n');


        }

        vista.setJlPrediccionFavoritos(sb.toString());

    }





    public void imprimirTiempo(int opcion, Data tiempo) {
        if (opcion == 1) {
            System.out.println(tiempo.informacionBasica());

        } else if (opcion == 2) {
            System.out.println(tiempo.informacionDetallada());
        }
    }


    public String getInfoTiempo(String opcion, Data tiempo) {
        String s;
        if (opcion.equals("Basica")) {
            s = tiempo.informacionBasica();
        } else {
            s = tiempo.informacionDetallada();
        }

        return s;
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

    public String getTiempo(Vista vista) {
        String orden = "";
        String[] coor = null;
        String ciudadCoordenada = vista.getBusqueda();
        if (vista.getTipoBusqueda().equals("Coordenada")) {
            coor = ciudadCoordenada.split(",");
        }
        String resultado = "";
        orden += vista.getTipoBusqueda() + "/";
        orden += vista.getTipoConsulta();
        String tipo = vista.getTipoInformacion();

        try {
            switch (orden) {
                case "Ciudad/Actual":
                    return getTiempoCiudadVista(ciudadCoordenada, tipo);

                case "Ciudad/Prediccion":
                    return getPrediccionCiudadVista(ciudadCoordenada);

                case "Coordenada/Prediccion":
                    return getPrediccionCoordenadasVista(Float.parseFloat(coor[0]), Float.parseFloat(coor[1]));

                case "Coordenada/Actual":
                    return getTiempoCoordenadasVista(Float.parseFloat(coor[0]), Float.parseFloat(coor[1]), tipo);

            }

        } catch (NumberFormatException e) {
            vista.formatoCoordenadasIncorrecto();
        }catch (ArrayIndexOutOfBoundsException e) {
            vista.formatoCoordenadasIncorrecto();
        }

        return resultado;

    }

    public BaseDatos getDB() {
        return baseDatos;
    }


    //SECCIÓN DE FAVORITOS
    /*public void getFavoritos(){
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

    }*/


}
