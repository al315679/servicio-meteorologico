package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;

public class BaseDatos implements Serializable {

    private TreeMap<String, Data> ciudadesActualBD;
    private TreeMap<String, Data> coordenadasActualBD;
    private TreeMap<String, Prediction> ciudadesPrediccionBD;
    private TreeMap<String, Prediction> coordenadasPrediciconBD;
    private TreeSet<String> ciudadesFavoritas;
    private TreeSet<String> coordenadasFavoritas;
    private TreeMap<String, Date> fechasBusquedaCiudad;
    private TreeMap<String, Date> fechasPrediccionCiudad;
    private TreeMap<String, Date> fechasBusquedaCoordenadas;
    private TreeMap<String, Date> fechasPrediccionCoordenadas;


    public BaseDatos() {
        this.ciudadesActualBD = new TreeMap<String, Data>();
        this.coordenadasActualBD = new TreeMap<String, Data>();
        this.ciudadesPrediccionBD = new TreeMap<String, Prediction>();
        this.coordenadasPrediciconBD = new TreeMap<String, Prediction>();
        this.ciudadesFavoritas = new TreeSet<String>();
        this.coordenadasFavoritas = new TreeSet<String>();
        this.fechasBusquedaCiudad = new TreeMap<String, Date>();
        this.fechasPrediccionCiudad = new TreeMap<String, Date>();
        this.fechasBusquedaCoordenadas = new TreeMap<String, Date>();
        this.fechasPrediccionCoordenadas = new TreeMap<String, Date>();
    }


    public void anadirTiempoCiudad(String ciudad, Data tiempo) {
        this.ciudadesActualBD.put(ciudad, tiempo);
    }

    public void anadirTiempoCoordenadas(String coordenadas, Data tiempo) {
        coordenadasActualBD.put(coordenadas, tiempo);
    }

    public void anadirPrediccionCiudad(String ciudad, Prediction prediccion) {
        this.ciudadesPrediccionBD.put(ciudad, prediccion);
    }

    public void anadirPrediccionCoordenadas(String coordenadas, Prediction prediccion) {
        coordenadasPrediciconBD.put(coordenadas, prediccion);
    }


    public boolean anadirCiudadFavorita(String ciudad) {
        return ciudadesFavoritas.add(ciudad);
    }

    public boolean borrarCiudadFavorita(String ciudad){
        return ciudadesFavoritas.remove(ciudad);
    }

    public boolean anadirCoordenadasFavoritas(String coordenadas) {
        return coordenadasFavoritas.add(coordenadas);
    }

    public boolean borrarCoordenadasFavoritas(String coordenadas) {
        return coordenadasFavoritas.remove(coordenadas);
    }


    //Diferencia de dias de Solicitud/Dias actual

    public int getDiasHastaHoyPrediccionCiudad(String ciudad) {
        Date diaSolicitud = fechasPrediccionCiudad.get(ciudad);
        return diferenciaDias(diaSolicitud);
    }

    public int getDiasHastaHoyBusquedaCiudad(String ciudad) {
        Date diaSolicitud = fechasBusquedaCiudad.get(ciudad);
        return diferenciaDias(diaSolicitud);
    }

    public int getDiasHastaHoyPrediccionCor(String cordenadas) {
        Date diaSolicitud = fechasPrediccionCoordenadas.get(cordenadas);
        return diferenciaDias(diaSolicitud);
    }

    public int getDiasHastaHoyBusquedaCor(String coordenadas) {
        Date diaSolicitud = fechasBusquedaCoordenadas.get(coordenadas);
        return diferenciaDias(diaSolicitud);
    }


    private int diferenciaDias(Date diaSolicitud) {
        java.util.Date fechaActual = new Date();
        System.out.println("Posible fecha actual: " + fechaActual.toString());
        int dias = (int) ((fechaActual.getTime() - diaSolicitud.getTime()) / 86400000);
        return dias;
    }


    //GUARDAR BUSQUEDA CONSULTAS O PREDICCIONES

    public void anadirFecha(String ciudad, TreeMap<String, Date> datos) {
        java.util.Date fechaActual = new Date();
        if (!datos.containsKey(ciudad)) {
            datos.put(ciudad, fechaActual);
        }
        datos.replace(ciudad, fechaActual);

    }


    //Borrar fechas viejas de solicitudes

    public void borrarSolicidudesViejas() {

        for (String ciudad : fechasBusquedaCiudad.keySet()) {
            if (getDiasHastaHoyBusquedaCiudad(ciudad) > 2) {
                fechasBusquedaCiudad.remove(ciudad);
                ciudadesActualBD.remove(ciudad);
            }
        }
        for (String ciudad : fechasPrediccionCiudad.keySet()) {
            if (getDiasHastaHoyPrediccionCiudad(ciudad) > 2) {
                fechasPrediccionCiudad.remove(ciudad);
                ciudadesPrediccionBD.remove(ciudad);
            }
        }
        for (String cor : fechasBusquedaCoordenadas.keySet()) {
            if (getDiasHastaHoyBusquedaCor(cor) > 2) {
                fechasBusquedaCoordenadas.remove(cor);
                coordenadasActualBD.remove(cor);
            }
        }
        for (String cor : fechasPrediccionCoordenadas.keySet()) {
            if (getDiasHastaHoyPrediccionCor(cor) > 2) {
                fechasPrediccionCoordenadas.remove(cor);
                coordenadasPrediciconBD.remove(cor);
            }
        }
    }


    public TreeSet<String> getCiudadesFavoritas() {

        return ciudadesFavoritas;
    }


    public TreeMap<String, Data> getCiudadesActualBD() {
        return ciudadesActualBD;
    }

    public void setCiudadesActualBD(TreeMap<String, Data> ciudadesActualBD) {
        this.ciudadesActualBD = ciudadesActualBD;
    }

    public TreeMap<String, Data> getCoordenadasActualBD() {
        return coordenadasActualBD;
    }

    public void setCoordenadasActualBD(TreeMap<String, Data> coordenadasActualBD) {
        this.coordenadasActualBD = coordenadasActualBD;
    }

    public TreeMap<String, Prediction> getCiudadesPrediccionBD() {
        return ciudadesPrediccionBD;
    }

    public void setCiudadesPrediccionBD(TreeMap<String, Prediction> ciudadesPrediccionBD) {
        this.ciudadesPrediccionBD = ciudadesPrediccionBD;
    }

    public TreeMap<String, Prediction> getCoordenadasPrediciconBD() {
        return coordenadasPrediciconBD;
    }

    public void setCoordenadasPrediciconBD(TreeMap<String, Prediction> coordenadasPrediciconBD) {
        this.coordenadasPrediciconBD = coordenadasPrediciconBD;
    }

    public TreeSet<String> getCoordenadasFavoritas() {
        return coordenadasFavoritas;
    }

    public void setCoordenadasFavoritas(TreeSet<String> coordenadasFavoritas) {
        this.coordenadasFavoritas = coordenadasFavoritas;
    }

    public void setCiudadesFavoritas(TreeSet<String> ciudadesFavoritas) {
        this.ciudadesFavoritas = ciudadesFavoritas;
    }


    public TreeMap<String, Date> getFechasBusquedaCiudadBD() {
        return this.fechasBusquedaCiudad;
    }

    public void setFechasBusquedaCiudadBD(TreeMap<String, Date> fechasBusquedaCiudadBD) {
        this.fechasBusquedaCiudad = fechasBusquedaCiudadBD;
    }

    public TreeMap<String, Date> getFechasPrediccionCiudadBD() {
        return fechasPrediccionCiudad;
    }

    public void setFechasPrediccionCiudadBD(TreeMap<String, Date> fechasPrediccionCiudadBD) {
        this.fechasPrediccionCiudad = fechasPrediccionCiudadBD;
    }

    public TreeMap<String, Date> getFechasBusquedaCoordenadaDB() {
        return this.fechasBusquedaCoordenadas;
    }

    public void setFechasBusquedaCoordenadas(TreeMap<String, Date> fechasBusquedaCoordenadas) {
        this.fechasBusquedaCoordenadas = fechasBusquedaCoordenadas;
    }

    public TreeMap<String, Date> getFechasPrediccionCoordenadaDB() {
        return this.fechasPrediccionCoordenadas;
    }

    public void setFechasPrediccionCoordenadas(TreeMap<String, Date> fechasPrediccionCoordenadas) {
        this.fechasPrediccionCoordenadas = fechasPrediccionCoordenadas;
    }
}
