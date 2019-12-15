package Model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List implements Serializable
{

    @SerializedName("dt")
    @Expose
    private int dt;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("snow")
    @Expose
    private Snow snow;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;
    private final static long serialVersionUID = -3705994921775912407L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public List() {
    }

    /**
     * 
     * @param dt
     * @param snow
     * @param dtTxt
     * @param weather
     * @param main
     * @param clouds
     * @param sys
     * @param wind
     */
    public List(int dt, Main main, java.util.List<Weather> weather, Clouds clouds, Wind wind, Snow snow, Sys sys, String dtTxt) {
        super();
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.clouds = clouds;
        this.wind = wind;
        this.snow = snow;
        this.sys = sys;
        this.dtTxt = dtTxt;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public List withDt(int dt) {
        this.dt = dt;
        return this;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List withMain(Main main) {
        this.main = main;
        return this;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public List withWeather(java.util.List<Weather> weather) {
        this.weather = weather;
        return this;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public List withClouds(Clouds clouds) {
        this.clouds = clouds;
        return this;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public List withWind(Wind wind) {
        this.wind = wind;
        return this;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public List withSnow(Snow snow) {
        this.snow = snow;
        return this;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List withSys(Sys sys) {
        this.sys = sys;
        return this;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public List withDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
        return this;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        //sb.append("dt=").append(dt);
        sb.append("Fecha predicción: ").append(dtTxt).append('\n');

        sb.append("Temperatura: ").append(main.getTemp()).append(" ºC").append('\n');
        sb.append("Temperatura mínima: ").append(main.getTempMin()).append(" ºC").append('\n');
        sb.append("Temperatura máxima: ").append(main.getTempMax()).append(" ºC").append('\n');
        sb.append("Humedad: ").append(main.getHumidity()).append("%").append('\n');
        sb.append("Presión atmosférica: ").append(main.getPressure()).append(" hpa").append('\n');
        sb.append("Presión a nivel del mar: ").append(main.getSeaLevel()).append(" hpa").append('\n');
        sb.append("Presión a nivel del suelo: ").append(main.getGrndLevel()).append(" hpa").append('\n');

        sb.append("Tiempo: ").append(weather);

        sb.append("Lluvias: ").append(clouds.getAll()).append('%').append('\n');

        sb.append("Viento ").append('\n');
        sb.append('\t').append("Velocidad: ").append(wind.getSpeed()).append(" m/s").append('\n');
        sb.append('\t').append("Grados: ").append(wind.getDeg()).append('\n');

        //sb.append("Nieve: ").append(snow.get3h()).append('\n');

        sb.append("___________________________________________________________________________________________");

        sb.append('\n');
        return sb.toString();
    }
}
