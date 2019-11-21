package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Tiempo {

    private final IntegerProperty id;
    private final StringProperty tiempo;
    private final StringProperty descripcion;

    public Tiempo(int id, String tiempo, String descripcion){
        this.id = new SimpleIntegerProperty(id);
        this.tiempo = new SimpleStringProperty(tiempo);
        this.descripcion = new SimpleStringProperty(descripcion);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTiempo() {
        return tiempo.get();
    }

    public StringProperty tiempoProperty() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo.set(tiempo);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tiempo tiempo1 = (Tiempo) o;
        return id.equals(tiempo1.id) &&
                tiempo.equals(tiempo1.tiempo) &&
                descripcion.equals(tiempo1.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tiempo, descripcion);
    }

    @Override
    public String toString() {
        return "Tiempo{" +
                "id=" + id +
                ", tiempo=" + tiempo +
                ", descripcion=" + descripcion +
                '}';
    }
}
