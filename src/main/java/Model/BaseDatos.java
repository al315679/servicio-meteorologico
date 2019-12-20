package Model;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.TreeSet;

public class BaseDatos implements Serializable {

    private TreeMap<String, Data> ciudadesActualBD;
    private TreeMap<String, Data> coordenadasActualBD;
    private TreeMap<String, Prediction> ciudadesPrediccionBD;
    private TreeMap<String, Prediction> coordenadasPrediciconBD;
    private TreeSet<String> ciudadesFavoritas;
    private TreeSet<String> coordenadasFavoritas;
    private TreeMap<String, Coord> etiquetasCoordenadas;


    public BaseDatos(){
        this.ciudadesActualBD = new TreeMap<String, Data>();
        this.coordenadasActualBD = new TreeMap<String, Data>();
        this.ciudadesPrediccionBD = new TreeMap<String, Prediction>();
        this.coordenadasPrediciconBD = new TreeMap<String, Prediction>();
        this.ciudadesFavoritas = new TreeSet<String>();
        this.coordenadasFavoritas = new TreeSet<String>();
        this.etiquetasCoordenadas = new TreeMap<>();

    }


    public void añadirTiempoCiudad(String ciudad, Data tiempo){
        this.ciudadesActualBD.put(ciudad, tiempo);
    }

    public void añadirTiempoCoordenadas(String coordenadas, Data tiempo){
        coordenadasActualBD.put(coordenadas, tiempo);
    }

    public void añadirPrediccionCiudad(String ciudad, Prediction prediccion){
        this.ciudadesPrediccionBD.put(ciudad, prediccion);
    }

    public void añadirPrediccionCoordenadas(String coordenadas, Prediction prediccion){
        coordenadasPrediciconBD.put(coordenadas, prediccion);
    }

    public void addEtiquetaCoordenadas(String etiqueta, Coord coord) {
        etiquetasCoordenadas.put(etiqueta, coord);
    }


    public boolean añadirCiudadFavorita(String ciudad){
        return ciudadesFavoritas.add(ciudad);
    }

    public boolean borrarCiudadFavorita(String ciudad){
        return ciudadesFavoritas.remove(ciudad);
    }

    public boolean añadirCoordenadasFavoritas(String coordenadas){
        return coordenadasFavoritas.add(coordenadas);
    }

    public boolean borrarCoordenadasFavoritas(String coordenadas){
        return coordenadasFavoritas.remove(coordenadas);
    }


    public TreeSet<String> getCiudadesFavoritas(){

      return ciudadesFavoritas;
    }

    public Coord getEtiquetaCoordenadas(String etiqueta) {
        return etiquetasCoordenadas.get(etiqueta);
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

}
