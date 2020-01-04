package Controller;

import Controller.Aplicacion;
import Model.Prediction;
import Services.IWeather;
import e2e.E2ETestBed;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class AplicacionPrediccionSinConexionTest extends E2ETestBed {
    private Aplicacion controller;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void getPrediccionCiudadSinConexion() throws IOException {
        IWeather mock = mock(IWeather.class);
        when(mock.getPrediccionCiudad("Valencia")).thenReturn(null);
        controller = new Aplicacion(mock);
        controller.getPrediccionCiudad("Valencia");
        verify(mock).getPrediccionCiudad(anyString());

    }

    @Test
    public void getPrediccionCoorSinConexion() throws IOException {
        IWeather mock = mock(IWeather.class);
        when(mock.getPrediccionCoordenadas(39.9924751, -0.067382)).thenReturn(null);
        controller = new Aplicacion(mock);
        controller.getPrediccionCoordenadas(39.9924751, -0.067382);
        verify(mock).getPrediccionCoordenadas(anyDouble(), anyDouble());

    }


}







