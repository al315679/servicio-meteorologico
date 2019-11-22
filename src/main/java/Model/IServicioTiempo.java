package Model;

import java.util.List;

public interface IServicioTiempo {

    DatosTiempo getTiempoCiudad(String nombre);

    DatosTiempo getTiempoCoordenadas(double longitud, double latitud);

    List<DatosTiempo> getPrediccionCiudad(String nombre);

    List<DatosTiempo> getPrediccionCoordenadas(double longitud, double latitud);


}
