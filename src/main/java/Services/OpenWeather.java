package Services;

import Model.Data;
import Model.Prediction;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenWeather implements Serializable {

    public Data getTiempoCiudad(String ciudad) throws IOException {
        URL urlForGetRequest = new URL("http://api.openweathermap.org/data/2.5/weather?q="+ciudad+"&lang=es&units=metric&APPID=723f605274ef9a756e6ffc652ad1d35a");
        return conexionAPICurrent(urlForGetRequest);
    }

    public Data getTiempoCoordenadas(double latitud, double longitud) throws IOException {
        URL urlForGetRequest = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+latitud+"&lon="+longitud+"&lang=es&units=metric&APPID=723f605274ef9a756e6ffc652ad1d35a");
        return conexionAPICurrent(urlForGetRequest);

    }

    public Prediction getPrediccionCiudad(String ciudad) throws IOException {
        URL urlForGetRequest = new URL("http://api.openweathermap.org/data/2.5/forecast?q="+ciudad+"&lang=es&units=metric&APPID=723f605274ef9a756e6ffc652ad1d35a");
        return conexionAPIForecast(urlForGetRequest);

    }

    public Prediction getPrediccionCoordenadas(double latitud, double longitud) throws IOException {
        URL urlForGetRequest = new URL("http://api.openweathermap.org/data/2.5/forecast?lat="+latitud+"&lon="+longitud+"&lang=es&units=metric&APPID=723f605274ef9a756e6ffc652ad1d35a");
        return conexionAPIForecast(urlForGetRequest);

    }

    private Data conexionAPICurrent(URL urlForGetRequest )throws IOException{
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();

            // print result
            //System.out.println("JSON String Result " + response.toString());

            String config_settings = response.toString();
            Gson converter = new Gson();
            Data settings = converter.fromJson(config_settings , Data.class);

            //System.out.println("Weather actual " + settings.toString());

            return settings;


        } else {
            System.out.println("GET NOT WORKED");
            return null;
        }
    }

    private Prediction conexionAPIForecast(URL urlForGetRequest )throws IOException{
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();

            // print result
            //System.out.println("JSON String Result " + response.toString());

            String config_settings = response.toString();
            Gson converter = new Gson();
            Prediction settings = converter.fromJson(config_settings , Prediction.class);

            //System.out.println("Weather prediccion " + settings.toString());

            return settings;


        } else {
            System.out.println("GET NOT WORKED");
            return null;
        }
    }

}
