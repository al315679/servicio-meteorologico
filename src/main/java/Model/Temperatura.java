package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects;

public class Temperatura {


    private final DoubleProperty temperaturaActual;
    private final DoubleProperty temperaturaMinima;
    private final DoubleProperty temperaturaMaxima;


    public Temperatura(double temperaturaActual, double temperaturaMinima, double temperaturaMaxima){
        this.temperaturaActual = new SimpleDoubleProperty(temperaturaActual);
        this.temperaturaMinima = new SimpleDoubleProperty(temperaturaMinima);
        this.temperaturaMaxima = new SimpleDoubleProperty(temperaturaMaxima);
    }


    public double getTemperaturaActual() {
        return temperaturaActual.get();
    }

    public DoubleProperty temperaturaActualProperty() {
        return temperaturaActual;
    }

    public void setTemperaturaActual(double temperaturaActual) {
        this.temperaturaActual.set(temperaturaActual);
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima.get();
    }

    public DoubleProperty temperaturaMinimaProperty() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(double temperaturaMinima) {
        this.temperaturaMinima.set(temperaturaMinima);
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima.get();
    }

    public DoubleProperty temperaturaMaximaProperty() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(double temperaturaMaxima) {
        this.temperaturaMaxima.set(temperaturaMaxima);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperatura that = (Temperatura) o;
        return temperaturaActual.equals(that.temperaturaActual) &&
                temperaturaMinima.equals(that.temperaturaMinima) &&
                temperaturaMaxima.equals(that.temperaturaMaxima);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperaturaActual, temperaturaMinima, temperaturaMaxima);
    }


    @Override
    public String toString() {
        return "Temperatura{" +
                "temperaturaActual=" + temperaturaActual +
                ", temperaturaMinima=" + temperaturaMinima +
                ", temperaturaMaxima=" + temperaturaMaxima +
                '}';
    }
}
