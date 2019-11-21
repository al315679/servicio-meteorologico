package Model;

import java.util.List;

public interface IServicioTiempo {

    public DatosTiempo getTiepoCiudad(String nombre);

    public DatosTiempo getTiempoCoordenadas(double longitud, double latitud);

    public List<DatosTiempo> getPrediccionCiudad(String nombre);

    public List<DatosTiempo> getPrediccionCoordenadas(double longitud, double latitud);


}
