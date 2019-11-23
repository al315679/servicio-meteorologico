package Model;

import View.PanelBusqueda;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PrediccionTiempoTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getPrediccionTiempoCiudadValidaSuc() {
        PanelBusqueda controller = new PanelBusqueda();
        Assert.assertNotNull(controller.getPrediccionCiudad("Valencia"));
    }

    @Test
    public void getPrediccionTiempoNoValida() {
        PanelBusqueda controller = new PanelBusqueda();
        thrown.expect(IllegalArgumentException.class);
        controller.getPrediccionCiudad("Mordor");
    }

    @Test
    public void getPrediccionTiempoCoordenadasValidasSuc() {
        PanelBusqueda controller = new PanelBusqueda();
        Assert.assertNotNull(controller.getPrediccionCoordenadas(39.9924751, -0.067382));
    }

    @Test
    public void getPrediccionTiempoCoordenadasNoValidas() {
        PanelBusqueda controller = new PanelBusqueda();
        thrown.expect(IllegalArgumentException.class);
        controller.getPrediccionCoordenadas(1111132.222222, -1021313.8388383); //Cambiar
    }
}
