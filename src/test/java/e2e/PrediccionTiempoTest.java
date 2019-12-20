package e2e;

import Controller.Aplicacion;
import e2e.E2ETestBed;
import org.junit.*;
import org.junit.rules.ExpectedException;

public class PrediccionTiempoTest extends E2ETestBed {


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void getPrediccionTiempoCiudadValida() {
        Assert.assertNotNull(controller.getPrediccionCiudad("Valencia"));
    }

    @Test
    public void getPrediccionTiempoNoValida() {
        thrown.expect(IllegalArgumentException.class);
        controller.getPrediccionCiudad("Mordor");
    }

    @Test
    public void getPrediccionTiempoCoordenadasValidas() {
        Assert.assertNotNull(controller.getPrediccionCoordenadas(39.9924751, -0.067382));
    }

    @Test
    public void getPrediccionTiempoCoordenadasNoValidas() {
        thrown.expect(IllegalArgumentException.class);
        controller.getPrediccionCoordenadas(1111132.222222, -1021313.8388383);
    }
}
