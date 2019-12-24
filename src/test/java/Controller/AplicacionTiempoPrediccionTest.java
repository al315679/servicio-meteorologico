package Controller;

import Services.IWeather;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AplicacionTiempoPrediccionTest {

    private Aplicacion controller;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void getTiempoPrediccionCiudadValidad() throws MalformedURLException {
        IWeather mock = mock(IWeather.class);
        controller = new Aplicacion(mock);
        controller.getPrediccionCiudad("Madrid");
        verify(mock).getPrediccionCiudad(any(String.class));

    }

    @Test
    public void getTiempoPrediccionCiudadNoValida() throws MalformedURLException {
        IWeather mock = mock(IWeather.class);
        when(mock.getPrediccionCiudad(any())).thenThrow(IllegalArgumentException.class);
        controller = new Aplicacion(mock);
        thrown.expect(IllegalArgumentException.class);
        controller.getPrediccionCiudad("Mordor");
    }

    @Test
    public void getTiempoPrediccionCoordenadasValidadas() throws IOException {
        IWeather mock = mock(IWeather.class);
        controller = new Aplicacion(mock);
        controller.getPrediccionCoordenadas(39.9924751, -0.067382);
        verify(mock).getPrediccionCoordenadas(anyDouble(), anyDouble());

    }

    @Test
    public void getTiempoPrediccionCoordenadasNoValidadas() throws IOException {
        IWeather mock = mock(IWeather.class);
        when(mock.getPrediccionCoordenadas(anyDouble(), anyDouble())).thenThrow(IllegalArgumentException.class);
        controller = new Aplicacion(mock);
        thrown.expect(IllegalArgumentException.class);
        controller.getPrediccionCoordenadas(1212212139.9924751, -4441110.067382);
    }

}
