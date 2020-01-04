package Services;

import Model.Data;
import Model.Prediction;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IWeather {
    Data getTiempoCiudad(String ciudad) throws IllegalArgumentException;

    Data getTiempoCoordenadas(double latitud, double longitud) throws IllegalArgumentException;

    Prediction getPrediccionCiudad(String ciudad) throws IllegalArgumentException;

    Prediction getPrediccionCoordenadas(double latitud, double longitud) throws IllegalArgumentException;
}
