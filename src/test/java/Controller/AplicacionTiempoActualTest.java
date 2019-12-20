package Controller;

import Services.IWeather;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AplicacionTiempoActualTest {

    private Aplicacion controller;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void getTiempoActualCiudadValidad() {
        IWeather mock = mock(IWeather.class);
        controller = new Aplicacion(mock);
        controller.getTiempoCiudad("Madrid");
        verify(mock).getTiempoCiudad(any(String.class));

    }

    @Test
    public void getTiempoActualCiudadNoValida() {
        IWeather mock = mock(IWeather.class);
        when(mock.getTiempoCiudad(any())).thenThrow(IllegalArgumentException.class);
        controller = new Aplicacion(mock);
        thrown.expect(IllegalArgumentException.class);
        controller.getTiempoCiudad("Mordor");
    }

    @Test
    public void getTiempoActualCoordenadasValidadas() {
        IWeather mock = mock(IWeather.class);
        controller = new Aplicacion(mock);
        controller.getTiempoCoordenadas(39.9924751, -0.067382);
        verify(mock).getTiempoCoordenadas(anyDouble(), anyDouble());

    }

    @Test
    public void getTiempoActualCoordenadasNoValidadas() {
        IWeather mock = mock(IWeather.class);
        when(mock.getTiempoCoordenadas(anyDouble(), anyDouble())).thenThrow(IllegalArgumentException.class);
        controller = new Aplicacion(mock);
        thrown.expect(IllegalArgumentException.class);
        controller.getTiempoCoordenadas(1212212139.9924751, -4441110.067382);
    }

}
