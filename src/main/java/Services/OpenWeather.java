
package Services;

import Model.Data;
import Model.Prediction;
import com.google.gson.*;
import javafx.scene.chart.ScatterChart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.URL;
import java.rmi.UnknownHostException;

public class OpenWeather implements Serializable, IWeather {

    @Override
    public Data getTiempoCiudad(String ciudad) throws IllegalArgumentException {

        Data conexion = null;
        URL urlForGetRequest= null;
        try {
            urlForGetRequest = new URL ("http://api.openweathermap.org/data/2.5/weather?q=" + ciudad + "&lang=es&units=metric&APPID=723f605274ef9a756e6ffc652ad1d35a");
            conexion = conexionAPICurrent(urlForGetRequest);
        }
        catch(MalformedURLException e){
            e.printStackTrace ();
        }
        return conexion;
    }

    @Override
    public Data getTiempoCoordenadas(double latitud, double longitud) throws IllegalArgumentException {

        Data conexion;
        URL urlForGetRequest= null;
        try {
            urlForGetRequest = new URL ("http://api.openweathermap.org/data/2.5/weather?lat=" + latitud + "&lon=" + longitud + "&lang=es&units=metric&APPID=723f605274ef9a756e6ffc652ad1d35a");
        }
        catch (MalformedURLException e ){
            e.printStackTrace ();
        }
        conexion = conexionAPICurrent(urlForGetRequest);

        return conexion;

    }

    @Override
    public Prediction getPrediccionCiudad(String ciudad) throws IllegalArgumentException {

        Prediction conexion;
        URL urlForGetRequest= null;
        try {
            urlForGetRequest = new URL ("http://api.openweathermap.org/data/2.5/forecast?q=" + ciudad + "&lang=es&units=metric&APPID=723f605274ef9a756e6ffc652ad1d35a");
        }
        catch (MalformedURLException e){
            e.printStackTrace ();
        }
        conexion = conexionAPIForecast(urlForGetRequest);

        return conexion;
    }

    @Override
    public Prediction getPrediccionCoordenadas(double latitud, double longitud) throws IllegalArgumentException {

        Prediction conexion;
        URL urlForGetRequest= null;
        try {
            urlForGetRequest = new URL ("http://api.openweathermap.org/data/2.5/forecast?lat=" + latitud + "&lon=" + longitud + "&lang=es&units=metric&APPID=723f605274ef9a756e6ffc652ad1d35a");
        }
        catch (MalformedURLException e){
            e.printStackTrace ();
        }
        conexion = conexionAPIForecast(urlForGetRequest);

        return conexion;

    }

    private Data conexionAPICurrent(URL urlForGetRequest) throws IllegalArgumentException {
        String config_settings = conexionAPI(urlForGetRequest);
        Gson converter = new Gson();
        Data settings = converter.fromJson(config_settings, Data.class);
        return settings;
    }

    private Prediction conexionAPIForecast(URL urlForGetRequest) throws IllegalArgumentException {
        String config_settings = conexionAPI(urlForGetRequest);
        Gson converter = new Gson();
        Prediction settings = converter.fromJson(config_settings, Prediction.class);
        return settings;

    }

    private String conexionAPI(URL urlForGetRequest) {
        String readLine = null;
        String config_settings = null;
        try {
            HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
            conection.setRequestMethod("GET");
            int responseCode = conection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null) {
                    response.append(readLine);
                }
                in.close();
                config_settings = response.toString();

            }

        } catch (java.net.UnknownHostException e) {
            return null;
        }
        catch (NoRouteToHostException e){
            return null;
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        if (config_settings == null) {
            throw new IllegalArgumentException();
        }

        return config_settings;

    }

}