package e2e;

import Controller.Aplicacion;
import Services.IWeather;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TiempoSinConexionTest extends E2ETestBed {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    //R6HU1


    //R6HU2
    @Test
    public void getTiempoCiudadGuardada() throws IOException {
        controller.getTiempoCiudad("Valencia");
        IWeather mock = mock(IWeather.class);
        when(mock.getTiempoCiudad("Valencia")).thenReturn(null);
        controller.setServicio(mock);
        Assert.assertNotNull(controller.getTiempoCiudad("Valencia"));


    }

    @Test
    public void getTiempoCiudadNoGuardada() throws IOException {
        IWeather mock = mock(IWeather.class);
        when(mock.getTiempoCiudad("Huesca")).thenReturn(null);
        controller = new Aplicacion(mock);
        Assert.assertNull(controller.getTiempoCiudad("Huesca"));

    }

    @Test
    public void getTiempoCoorGuardada() throws IOException {

        controller.getTiempoCoordenadas(1.0, 1.0);
        IWeather mock = mock(IWeather.class);
        when(mock.getTiempoCoordenadas(1.0, 1.0)).thenReturn(null);
        controller.setServicio(mock);
        Assert.assertNotNull(controller.getTiempoCoordenadas(1.0, 1.0));


    }

    @Test
    public void getTiempoCoorNoGuardada() throws IOException {
        IWeather mock = mock(IWeather.class);
        when(mock.getTiempoCoordenadas(2.0, 2.0)).thenReturn(null);
        controller = new Aplicacion(mock);
        Assert.assertNull(controller.getTiempoCoordenadas(2.0, 2.0));


    }
}
