package Services;

import Model.Data;
import Model.Prediction;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IWeather {
    Data getTiempoCiudad(String ciudad) throws IllegalArgumentException, MalformedURLException;

    Data getTiempoCoordenadas(double latitud, double longitud) throws IllegalArgumentException, MalformedURLException;

    Prediction getPrediccionCiudad(String ciudad) throws IllegalArgumentException, MalformedURLException;

    Prediction getPrediccionCoordenadas(double latitud, double longitud) throws IllegalArgumentException, IOException;
}
