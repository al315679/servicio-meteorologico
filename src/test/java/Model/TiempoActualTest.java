package Model;

import View.PanelBusqueda;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TiempoActualTest { //Añadir nuevas historias

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getTiempoActualCiudadValida() {
        PanelBusqueda controller = new PanelBusqueda();
        Assert.assertNotNull(controller.getTiempoCiudad("Valencia"));
    }

    @Test
    public void getTiempoActualCiudadNoValida() {
        PanelBusqueda controller = new PanelBusqueda();
        thrown.expect(IllegalArgumentException.class);
        controller.getTiempoCiudad("Mordor");
    }

    @Test
    public void getTiempoActualCoordenadasValidas() {
        PanelBusqueda controller = new PanelBusqueda();
        Assert.assertNotNull(controller.getTiempoCoordenadas(39.9924751, -0.067382));
    }

    @Test
    public void getTiempoActualCoordenadasNoValidas() {
        PanelBusqueda controller = new PanelBusqueda();
        thrown.expect(IllegalArgumentException.class);
        controller.getTiempoCoordenadas(1111132.222222, -1021313.8388383); //Cambiar
    }
}
