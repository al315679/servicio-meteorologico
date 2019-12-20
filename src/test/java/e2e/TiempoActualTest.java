package e2e;

import Controller.Aplicacion;
import Model.Data;
import e2e.E2ETestBed;
import org.junit.*;
import org.junit.rules.ExpectedException;

public class TiempoActualTest extends E2ETestBed {


    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void getTiempoActualCiudadValida() {
        Assert.assertNotNull(controller.getTiempoCiudad("Valencia"));
    }

    @Test
    public void getTiempoActualCiudadNoValida() {
        thrown.expect(IllegalArgumentException.class);
        controller.getTiempoCiudad("Mordor");
    }

    @Test
    public void getTiempoActualCoordenadasValidas() {
        Assert.assertNotNull(controller.getTiempoCoordenadas(39.9924751, -0.067382));
    }

    @Test
    public void getTiempoActualCoordenadasNoValidas() {
        thrown.expect(IllegalArgumentException.class);
        controller.getTiempoCoordenadas(1111132.222222, -1021313.8388383);
    }
}
