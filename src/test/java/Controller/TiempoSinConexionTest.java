package Controller;

import Controller.Aplicacion;
import Services.IWeather;
import e2e.E2ETestBed;
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
        String res;
        res = controller.getTiempoCiudadVista("Valencia", "Basica");
        Assert.assertEquals ("Este estado de tiempo se busco hace menos de una hora"+'\n'+res, controller.getTiempoCiudadVista("Valencia", "Basica"));
    }

    @Test
    public void getTiempoCiudadNoGuardada() throws IOException {
        IWeather mock = mock(IWeather.class);
        when(mock.getTiempoCiudad("Huesca")).thenReturn(null);
        controller = new Aplicacion(mock);
        Assert.assertEquals ("No hay conexion con el api"+'\n', controller.getTiempoCiudadVista ("Huesca", "Basica"));
    }

    @Test
    public void getTiempoCoorGuardada() throws IOException {
        String res;
        res = controller.getTiempoCoordenadasVista(1.1f, 1.1f, "Basica");
        IWeather mock = mock(IWeather.class);
        when(mock.getTiempoCoordenadas(1.1f, 1.1f)).thenReturn(null);
        controller.setServicio(mock);
        Assert.assertEquals ("Este estado de tiempo se busco hace menos de una hora"+'\n'+res,controller.getTiempoCoordenadasVista(1.1f, 1.1f,"Basica"));


    }

    @Test
    public void getTiempoCoorNoGuardada() throws IOException {
        IWeather mock = mock(IWeather.class);
        when(mock.getTiempoCoordenadas(2.0f, 2.0f)).thenReturn(null);
        controller = new Aplicacion(mock);
        Assert.assertEquals ("No hay conexion con el api"+'\n', controller.getTiempoCoordenadasVista(2.0f, 2.0f, "Basica"));


    }
}
