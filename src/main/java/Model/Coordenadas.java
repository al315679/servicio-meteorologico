package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects;

public class Coordenadas {

    private final DoubleProperty longitud;
    private final DoubleProperty latitud;

    public Coordenadas(double longitud, double latitud){
        this.longitud = new SimpleDoubleProperty(longitud);
        this.latitud = new SimpleDoubleProperty(latitud);
    }

    public double getLongitud() {
        return longitud.get();
    }

    public DoubleProperty longitudProperty() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud.set(longitud);
    }

    public double getLatitud() {
        return latitud.get();
    }

    public DoubleProperty latitudProperty() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud.set(latitud);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordenadas that = (Coordenadas) o;
        return longitud.equals(that.longitud) &&
                latitud.equals(that.latitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitud, latitud);
    }

    @Override
    public String toString() {
        return "Coordenadas{" +
                "longitud=" + longitud +
                ", latitud=" + latitud +
                '}';
    }
}
