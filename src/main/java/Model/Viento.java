package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects;

public class Viento {

    private final DoubleProperty velocidad;
    private final DoubleProperty grados;

    public Viento(double velocidad, double grados){
        this.velocidad = new SimpleDoubleProperty(velocidad);
        this.grados = new SimpleDoubleProperty(grados);
    }


    public double getVelocidad() {
        return velocidad.get();
    }

    public DoubleProperty velocidadProperty() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad.set(velocidad);
    }

    public double getGrados() {
        return grados.get();
    }

    public DoubleProperty gradosProperty() {
        return grados;
    }

    public void setGrados(double grados) {
        this.grados.set(grados);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Viento viento = (Viento) o;
        return velocidad.equals(viento.velocidad) &&
                grados.equals(viento.grados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(velocidad, grados);
    }

    @Override
    public String toString() {
        return "Viento{" +
                "velocidad=" + velocidad +
                ", grados=" + grados +
                '}';
    }
}
