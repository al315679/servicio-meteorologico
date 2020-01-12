package Model;

import View.InterfaceVista;
import View.Vista;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;

public class BaseDatos implements Serializable, IBaseDatos {

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
    private TreeMap<String, Coord> etiquetasCoordenadas;

    @SuppressWarnings("unused")
    private InterfaceVista vista;

    @Override
    public void setVista(Vista vista) {
        this.vista = vista;
    }

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
        this.etiquetasCoordenadas = new TreeMap<>();

    }


    @Override
    public void anadirTiempoCiudad(String ciudad, Data tiempo) {
        this.ciudadesActualBD.put(ciudad, tiempo);
    }

    @Override
    public void anadirTiempoCoordenadas(String coordenadas, Data tiempo) {
        coordenadasActualBD.put(coordenadas, tiempo);
    }

    @Override
    public void anadirPrediccionCiudad(String ciudad, Prediction prediccion) {
        this.ciudadesPrediccionBD.put(ciudad, prediccion);
    }

    @Override
    public void anadirPrediccionCoordenadas(String coordenadas, Prediction prediccion) {
        coordenadasPrediciconBD.put(coordenadas, prediccion);
    }

    @Override
    public void addEtiquetaCoordenadas(String etiqueta, Coord coord) {
        etiquetasCoordenadas.put(etiqueta, coord);
    }


    @Override
    public boolean anadirCiudadFavorita(String ciudad) {
        return ciudadesFavoritas.add(ciudad);
    }

    @Override
    public boolean borrarCiudadFavorita(String ciudad){
        return ciudadesFavoritas.remove(ciudad);
    }

    @Override
    public boolean anadirCoordenadasFavoritas(String coordenadas) {
        return coordenadasFavoritas.add(coordenadas);
    }

    @Override
    public boolean borrarCoordenadasFavoritas(String coordenadas) {
        return coordenadasFavoritas.remove(coordenadas);
    }


    //Diferencia de dias de Solicitud/Dias actual

    @Override
    public int getDiasHastaHoyPrediccionCiudad(String ciudad, Boolean horas) {
        Date diaSolicitud = fechasPrediccionCiudad.get(ciudad);
        return diferenciaDiasHoras(diaSolicitud, horas);
    }

    @Override
    public int getDiasHastaHoyBusquedaCiudad(String ciudad, Boolean horas) {
        Date diaSolicitud = fechasBusquedaCiudad.get(ciudad);
        return diferenciaDiasHoras(diaSolicitud, horas);
    }

    @Override
    public int getDiasHastaHoyPrediccionCor(String cordenadas, Boolean horas) {
        Date diaSolicitud = fechasPrediccionCoordenadas.get(cordenadas);
        return diferenciaDiasHoras(diaSolicitud, horas);
    }

    @Override
    public int getDiasHastaHoyBusquedaCor(String coordenadas, Boolean horas) {
        Date diaSolicitud = fechasBusquedaCoordenadas.get(coordenadas);
        return diferenciaDiasHoras(diaSolicitud, horas);
    }


    private int diferenciaDiasHoras(Date diaSolicitud, Boolean Horas) {
        java.util.Date fechaActual = new Date();
        int dias;
        int horas;
        System.out.println("Posible fecha actual: " + fechaActual.toString());
        System.out.println ("Fecha solicitud: "+ diaSolicitud.toString ());
        long diasLong =  fechaActual.getTime() - diaSolicitud.getTime();
        horas= (int) diasLong / 3600000;
        dias = (int) diasLong / 86400000 ;
        System.out.println ("Dias: "+dias+ " Horas: "+horas);
        if (Horas) return horas;
        return dias;
    }


    //GUARDAR BUSQUEDA CONSULTAS O PREDICCIONES

    @Override
    public void anadirFecha(String ciudad, TreeMap<String, Date> datos) {
        java.util.Date fechaActual = new Date();
        if (!datos.containsKey(ciudad)) {
            datos.put(ciudad, fechaActual);
        }
        datos.replace(ciudad, fechaActual);

    }


    //Borrar fechas viejas de solicitudes

    @Override
    public void borrarSolicidudesViejas() {

        for (String ciudad : fechasBusquedaCiudad.keySet()) {
            if (getDiasHastaHoyBusquedaCiudad(ciudad,false) > 2) {
                fechasBusquedaCiudad.remove(ciudad);
                ciudadesActualBD.remove(ciudad);
            }
        }
        for (String ciudad : fechasPrediccionCiudad.keySet()) {
            if (getDiasHastaHoyPrediccionCiudad(ciudad, false) > 2) {
                fechasPrediccionCiudad.remove(ciudad);
                ciudadesPrediccionBD.remove(ciudad);
            }
        }
        for (String cor : fechasBusquedaCoordenadas.keySet()) {
            if (getDiasHastaHoyBusquedaCor(cor, false) > 2) {
                fechasBusquedaCoordenadas.remove(cor);
                coordenadasActualBD.remove(cor);
            }
        }
        for (String cor : fechasPrediccionCoordenadas.keySet()) {
            if (getDiasHastaHoyPrediccionCor(cor, false) > 2) {
                fechasPrediccionCoordenadas.remove(cor);
                coordenadasPrediciconBD.remove(cor);
            }
        }
    }


    @Override
    public TreeSet<String> getCiudadesFavoritas() {

        return ciudadesFavoritas;
    }

    @Override
    public Coord getEtiquetaCoordenadas(String etiqueta) {
        return etiquetasCoordenadas.get(etiqueta);
    }


    @Override
    public TreeMap<String, Data> getCiudadesActualBD() {
        return ciudadesActualBD;
    }

    @Override
    public void setCiudadesActualBD(TreeMap<String, Data> ciudadesActualBD) {
        this.ciudadesActualBD = ciudadesActualBD;
    }

    @Override
    public TreeMap<String, Data> getCoordenadasActualBD() {
        return coordenadasActualBD;
    }

    @Override
    public void setCoordenadasActualBD(TreeMap<String, Data> coordenadasActualBD) {
        this.coordenadasActualBD = coordenadasActualBD;
    }

    @Override
    public TreeMap<String, Prediction> getCiudadesPrediccionBD() {
        return ciudadesPrediccionBD;
    }

    @Override
    public void setCiudadesPrediccionBD(TreeMap<String, Prediction> ciudadesPrediccionBD) {
        this.ciudadesPrediccionBD = ciudadesPrediccionBD;
    }

    @Override
    public TreeMap<String, Prediction> getCoordenadasPrediciconBD() {
        return coordenadasPrediciconBD;
    }

    @Override
    public void setCoordenadasPrediciconBD(TreeMap<String, Prediction> coordenadasPrediciconBD) {
        this.coordenadasPrediciconBD = coordenadasPrediciconBD;
    }

    @Override
    public TreeSet<String> getCoordenadasFavoritas() {
        return coordenadasFavoritas;
    }

    @Override
    public void setCoordenadasFavoritas(TreeSet<String> coordenadasFavoritas) {
        this.coordenadasFavoritas = coordenadasFavoritas;
    }

    @Override
    public void setCiudadesFavoritas(TreeSet<String> ciudadesFavoritas) {
        this.ciudadesFavoritas = ciudadesFavoritas;
    }

    @Override
    public TreeMap<String, Date> getFechasBusquedaCiudadBD() {
        return this.fechasBusquedaCiudad;
    }

    @Override
    public void setFechasBusquedaCiudadBD(TreeMap<String, Date> fechasBusquedaCiudadBD) {
        this.fechasBusquedaCiudad = fechasBusquedaCiudadBD;
    }

    @Override
    public TreeMap<String, Date> getFechasPrediccionCiudadBD() {
        return fechasPrediccionCiudad;
    }

    @Override
    public void setFechasPrediccionCiudadBD(TreeMap<String, Date> fechasPrediccionCiudadBD) {
        this.fechasPrediccionCiudad = fechasPrediccionCiudadBD;
    }

    @Override
    public TreeMap<String, Date> getFechasBusquedaCoordenadaDB() {
        return this.fechasBusquedaCoordenadas;
    }

    @Override
    public void setFechasBusquedaCoordenadas(TreeMap<String, Date> fechasBusquedaCoordenadas) {
        this.fechasBusquedaCoordenadas = fechasBusquedaCoordenadas;
    }

    @Override
    public TreeMap<String, Date> getFechasPrediccionCoordenadaDB() {
        return this.fechasPrediccionCoordenadas;
    }

    @Override
    public void setFechasPrediccionCoordenadas(TreeMap<String, Date> fechasPrediccionCoordenadas) {
        this.fechasPrediccionCoordenadas = fechasPrediccionCoordenadas;
    }
}
