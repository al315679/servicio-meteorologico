package e2e;

import Controller.Aplicacion;
import Model.Coord;
import Services.IWeather;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.net.ConnectException;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;


public class PrediccionSinConexionTest extends E2ETestBed {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    //R6HU1


    //R6HU2
    @Test
    public void getPrediccionCiudadGuardada() throws IOException {
        controller.getPrediccionCiudad("Valencia");
        IWeather mock = mock(IWeather.class);
        when(mock.getPrediccionCiudad("Valencia")).thenReturn(null);
        controller.setServicio(mock);
        Assert.assertNotNull(controller.getPrediccionCiudad("Valencia"));


    }

    @Test
    public void getPrediccionCiudadNoGuardada() throws IOException {
        IWeather mock = mock(IWeather.class);
        when(mock.getPrediccionCiudad("Huesca")).thenReturn(null);
        controller = new Aplicacion(mock);
        Assert.assertNull(controller.getPrediccionCiudad("Huesca"));

    }

    @Test
    public void getPrediccionCoorGuardada() throws IOException {
        controller.getPrediccionCoordenadas(1.0, 1.0);
        IWeather mock = mock(IWeather.class);
        when(mock.getPrediccionCoordenadas(1.0, 1.0)).thenReturn(null);
        controller.setServicio(mock);
        Assert.assertNotNull(controller.getPrediccionCoordenadas(1.0, 1.0));


    }

    @Test
    public void getPrediccionCoorNoGuardada() throws IOException {
        IWeather mock = mock(IWeather.class);
        when(mock.getPrediccionCoordenadas(2.0, 2.0)).thenReturn(null);
        controller = new Aplicacion(mock);
        Assert.assertNull(controller.getPrediccionCoordenadas(2.0, 2.0));


    }


}
