package Controller;

import Model.*;
import Services.IWeather;
import org.junit.After;

import org.junit.Before;

import org.junit.Test;


import java.util.ArrayList;
import java.util.TreeMap;

import static org.mockito.Mockito.*;

public class AplicacionTiempoSinConexionTest {

    private Aplicacion controller;
    private TreeMap<String, Data> dataTreeMap;


    @Before
    public void setUp() {
        dataTreeMap = new TreeMap<>();
        ArrayList<Weather> wea = new ArrayList<>();
        wea.add(new Weather());

        dataTreeMap.put("Valencia", new Data(new Coord(-0.83, 39.33),
                wea, "algo", new Main(), 15, new Wind(),
                new Clouds(), new Sys(), 12, "Valencia"));

        dataTreeMap.put("39.992474, -0.067382", new Data(new Coord(-0.83, 39.33),
                wea, "algo", new Main(), 15, new Wind(),
                new Clouds(), new Sys(), 12, "Valencia"));

    }

    @After
    public void tearDown() {
        controller = null;
    }

    @Test
    public void getTiempoCiudadSinConexion_Suc() {
        IBaseDatos mockBD = mock(IBaseDatos.class);
        when(mockBD.getCiudadesActualBD()).thenReturn(dataTreeMap);
        when(mockBD.getDiasHastaHoyBusquedaCiudad("Valencia", true)).thenReturn(1);

        IWeather mockAPI = mock(IWeather.class);
        controller = new Aplicacion(mockAPI, mockBD);

        controller.getTiempoCiudadVista("Valencia", "Basica");

        verify(mockAPI, never()).getTiempoCiudad(anyString());
    }

    @Test
    public void getTiempoCoorSinConexion_Suc() {
        IBaseDatos mockBD = mock(IBaseDatos.class);
        when(mockBD.getCoordenadasActualBD()).thenReturn(dataTreeMap);
        when(mockBD.getDiasHastaHoyBusquedaCor("39.992474, -0.067382", true)).thenReturn(1);

        IWeather mockAPI = mock(IWeather.class);
        controller = new Aplicacion(mockAPI, mockBD);

        controller.getTiempoCoordenadasVista(39.992474f, -0.067382f, "Basica");
        verify(mockAPI, never()).getTiempoCoordenadas(anyDouble(), anyDouble());

    }


}








