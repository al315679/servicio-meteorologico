package View;

import Model.DatosTiempo;
import Model.IServicioTiempo;

import java.util.List;

public class PanelBusqueda {

    private IServicioTiempo servicio;

    public IServicioTiempo getServicio() {
        return servicio;
    }

    public void setServicio(IServicioTiempo servicio) {
        this.servicio = servicio;
    }


    public DatosTiempo getTiepoCiudad(String nombre){
        throw new UnsupportedOperationException("Método no implementado");
    }

    public DatosTiempo getTiempoCoordenadas(double longitud, double latitud){
        throw new UnsupportedOperationException("Método no implementado");
    }

    public List<DatosTiempo> getPrediccionCiudad(String nombre){
        throw new UnsupportedOperationException("Método no implementado");
    }

    public List<DatosTiempo> getPrediccionCoordenadas(double longitud, double latitud){
        throw new UnsupportedOperationException("Método no implementado");
    }
}
