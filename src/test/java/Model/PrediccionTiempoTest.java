package Model;

import View.PanelBusqueda;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PrediccionTiempoTest {

    @Rule
    ExpectedException thrown = ExpectedException.none();

    @Test
    void getPrediccionTiempoCiudadValida() {
        PanelBusqueda controller = new PanelBusqueda();
        Assert.assertNotNull(controller.getPrediccionCiudad("Valencia"));
    }

    @Test
    void getPrediccionTiempoNoValida() {
        PanelBusqueda controller = new PanelBusqueda();
        thrown.expect(InvalidArgumentException.class);
        controller.getPrediccionCiudad("Mordor");
    }

    @Test
    void getPrediccionTiempoCoordenadasValidas() {
        PanelBusqueda controller = new PanelBusqueda();
        Assert.assertNotNull(controller.getPrediccionCoordenadas(39.9924751, -0.067382));
    }

    @Test
    void getPrediccionTiempoCoordenadasNoValidas() {
        PanelBusqueda controller = new PanelBusqueda();
        thrown.expect(InvalidArgumentException.class);
        controller.getPrediccionCoordenadas(1111132.222222, -1021313.8388383); //Cambiar
    }
}
