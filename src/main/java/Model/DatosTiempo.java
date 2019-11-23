package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.time.LocalDateTime;
import java.util.Objects;

public class DatosTiempo {

    private final LocalDateTime fecha;
    private final Ciudad ciudad;
    private final Tiempo tiempo;
    private final Temperatura temperatura;
    private final Viento viento;
    private final DoubleProperty presion;
    private final DoubleProperty humedad;

    public DatosTiempo(LocalDateTime fecha, Ciudad ciudad, Tiempo tiempo, Temperatura temperatura, Viento viento, double presion, double humedad){

        this.fecha = fecha;
        this.ciudad = ciudad;
        this.tiempo = tiempo;
        this.temperatura = temperatura;
        this.viento = viento;
        this.presion = new SimpleDoubleProperty(presion);
        this.humedad = new SimpleDoubleProperty(humedad);
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public Tiempo getTiempo() {
        return tiempo;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public Viento getViento() {
        return viento;
    }

    public double getPresion() {
        return presion.get();
    }

    public DoubleProperty presionProperty() {
        return presion;
    }

    public void setPresion(double presion) {
        this.presion.set(presion);
    }

    public double getHumedad() {
        return humedad.get();
    }

    public DoubleProperty humedadProperty() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad.set(humedad);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatosTiempo that = (DatosTiempo) o;
        return fecha.equals(that.fecha) &&
                ciudad.equals(that.ciudad) &&
                tiempo.equals(that.tiempo) &&
                temperatura.equals(that.temperatura) &&
                viento.equals(that.viento) &&
                presion.equals(that.presion) &&
                humedad.equals(that.humedad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, ciudad, tiempo, temperatura, viento, presion, humedad);
    }

    @Override
    public String toString() {
        return "DatosTiempo{" +
                "fecha=" + fecha +
                ", ciudad=" + ciudad +
                ", tiempo=" + tiempo +
                ", temperatura=" + temperatura +
                ", viento=" + viento +
                ", presion=" + presion +
                ", humedad=" + humedad +
                '}';
    }
}
