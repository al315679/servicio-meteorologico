package Model;

import View.PanelBusqueda;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TiempoActualTest { //Poner varias historias??

    @Rule
    ExpectedException thrown = ExpectedException.none();

    @Test
    void getTiempoActualCiudadValida() {
        PanelBusqueda controller = new PanelBusqueda();
        Assert.assertNotNull(controller.getTiempoCiudad("Valencia"));
    }

    @Test
    void getTiempoActualCiudadNoValida() {
        PanelBusqueda controller = new PanelBusqueda();
        thrown.expect(InvalidArgumentException.class);
        controller.getTiempoCiudad("Mordor");
    }

    @Test
    void getTiempoActualCoordenadasValidas() {
        PanelBusqueda controller = new PanelBusqueda();
        Assert.assertNotNull(controller.getTiempoCoordenadas(39.9924751, -0.067382));
    }

    @Test
    void getTiempoActualCoordenadasNoValidas() {
        PanelBusqueda controller = new PanelBusqueda();
        thrown.expect(InvalidArgumentException.class);
        controller.getTiempoCoordenadas(1111132.222222, -1021313.8388383); //Cambiar
    }
}
