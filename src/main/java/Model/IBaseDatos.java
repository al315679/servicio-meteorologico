package Model;

import View.Vista;

import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;

public interface IBaseDatos {
    void setVista(Vista vista);

    void anadirTiempoCiudad(String ciudad, Data tiempo);

    void anadirTiempoCoordenadas(String coordenadas, Data tiempo);

    void anadirPrediccionCiudad(String ciudad, Prediction prediccion);

    void anadirPrediccionCoordenadas(String coordenadas, Prediction prediccion);

    void addEtiquetaCoordenadas(String etiqueta, Coord coord);

    boolean anadirCiudadFavorita(String ciudad);

    boolean borrarCiudadFavorita(String ciudad);

    boolean anadirCoordenadasFavoritas(String coordenadas);

    boolean borrarCoordenadasFavoritas(String coordenadas);

    int getDiasHastaHoyPrediccionCiudad(String ciudad, Boolean horas);

    int getDiasHastaHoyBusquedaCiudad(String ciudad, Boolean horas);

    int getDiasHastaHoyPrediccionCor(String cordenadas, Boolean horas);

    int getDiasHastaHoyBusquedaCor(String coordenadas, Boolean horas);

    void anadirFecha(String ciudad, TreeMap<String, Date> datos);

    void borrarSolicidudesViejas();

    TreeSet<String> getCiudadesFavoritas();

    Coord getEtiquetaCoordenadas(String etiqueta);

    TreeMap<String, Data> getCiudadesActualBD();

    void setCiudadesActualBD(TreeMap<String, Data> ciudadesActualBD);

    TreeMap<String, Data> getCoordenadasActualBD();

    void setCoordenadasActualBD(TreeMap<String, Data> coordenadasActualBD);

    TreeMap<String, Prediction> getCiudadesPrediccionBD();

    void setCiudadesPrediccionBD(TreeMap<String, Prediction> ciudadesPrediccionBD);

    TreeMap<String, Prediction> getCoordenadasPrediciconBD();

    void setCoordenadasPrediciconBD(TreeMap<String, Prediction> coordenadasPrediciconBD);

    TreeSet<String> getCoordenadasFavoritas();

    void setCoordenadasFavoritas(TreeSet<String> coordenadasFavoritas);

    void setCiudadesFavoritas(TreeSet<String> ciudadesFavoritas);

    TreeMap<String, Date> getFechasBusquedaCiudadBD();

    void setFechasBusquedaCiudadBD(TreeMap<String, Date> fechasBusquedaCiudadBD);

    TreeMap<String, Date> getFechasPrediccionCiudadBD();

    void setFechasPrediccionCiudadBD(TreeMap<String, Date> fechasPrediccionCiudadBD);

    TreeMap<String, Date> getFechasBusquedaCoordenadaDB();

    void setFechasBusquedaCoordenadas(TreeMap<String, Date> fechasBusquedaCoordenadas);

    TreeMap<String, Date> getFechasPrediccionCoordenadaDB();

    void setFechasPrediccionCoordenadas(TreeMap<String, Date> fechasPrediccionCoordenadas);
}
