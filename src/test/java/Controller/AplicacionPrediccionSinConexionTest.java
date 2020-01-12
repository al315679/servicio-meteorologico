package Controller;

import Model.*;
import Services.IWeather;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.mockito.Mockito.*;

public class AplicacionPrediccionSinConexionTest {

    private Aplicacion controller;
    private TreeMap<String, Prediction> predictionTreeMap;

    @Before
    public void setUp() {
        predictionTreeMap = new TreeMap<>();
        predictionTreeMap.put("Valencia", new Prediction("12", 0.2,
                1, new ArrayList<>(), new City(1, "Valencia", new Coord(-0.83, 39.33), "ES")));
        predictionTreeMap.put("39.992474, -0.067382", new Prediction("12", 0.2,
                1, new ArrayList<>(), new City(1, "Valencia", new Coord(-0.83, 39.33), "ES")));
    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void getPrediccionCiudadSinConexion_Suc() {
        IBaseDatos mockBD = mock(IBaseDatos.class);
        when(mockBD.getCiudadesPrediccionBD()).thenReturn(predictionTreeMap);
        when(mockBD.getDiasHastaHoyBusquedaCiudad("Valencia", true)).thenReturn(1);

        IWeather mockAPI = mock(IWeather.class);
        controller = new Aplicacion(mockAPI, mockBD);

        controller.getPrediccionCiudadVista("Valencia");
        verify(mockAPI, never()).getPrediccionCiudad(anyString());
    }

    @Test
    public void getPrediccionCoorSinConexion_Suc() {
        IBaseDatos mockBD = mock(IBaseDatos.class);
        when(mockBD.getCoordenadasPrediciconBD()).thenReturn(predictionTreeMap);
        when(mockBD.getDiasHastaHoyPrediccionCor("39.992474, -0.067382", true)).thenReturn(1);

        IWeather mockAPI = mock(IWeather.class);
        controller = new Aplicacion(mockAPI, mockBD);

        controller.getPrediccionCoordenadasVista(39.992474f, -0.067382f);
        verify(mockAPI, never()).getPrediccionCoordenadas(anyDouble(), anyDouble());
    }


}







