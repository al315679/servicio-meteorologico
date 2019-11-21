package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Ciudad {

    private final IntegerProperty id;
    private final StringProperty nombre;
    private final StringProperty pais;


    public Ciudad(int id, String nombre, String pais){

        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.pais = new SimpleStringProperty(pais);

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

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getPais() {
        return pais.get();
    }

    public StringProperty paisProperty() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais.set(pais);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudad ciudad = (Ciudad) o;
        return id.equals(ciudad.id) &&
                nombre.equals(ciudad.nombre) &&
                pais.equals(ciudad.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, pais);
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", pais=" + pais +
                '}';
    }
}
