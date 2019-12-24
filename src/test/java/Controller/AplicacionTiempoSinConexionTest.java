package Controller;

import Services.IWeather;
import e2e.E2ETestBed;
import org.junit.After;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class AplicacionTiempoSinConexionTest extends E2ETestBed {
    private Aplicacion controller;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void getTiempoCiudadSinConexion() throws IOException {
        IWeather mock = mock(IWeather.class);
        when(mock.getTiempoCiudad("Valencia")).thenReturn(null);
        controller = new Aplicacion(mock);
        controller.getTiempoCiudad("Valencia");
        verify(mock).getTiempoCiudad(anyString());

    }

    @Test
    public void getTiempoCoorSinConexion() throws IOException {
        IWeather mock = mock(IWeather.class);
        when(mock.getTiempoCoordenadas(39.9924751, -0.067382)).thenReturn(null);
        controller = new Aplicacion(mock);
        controller.getTiempoCoordenadas(39.9924751, -0.067382);
        verify(mock).getTiempoCoordenadas(anyDouble(), anyDouble());

    }


}








