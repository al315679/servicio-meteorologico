package Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("visibility")
    @Expose
    private int visibility;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
/*    @SerializedName("dt")
    @Expose
    private int dt;*/
    @SerializedName("sys")
    @Expose
    private Sys sys;
/*    @SerializedName("timezone")
    @Expose
    private int timezone;*/
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
/*    @SerializedName("cod")
    @Expose
    private int cod;*/
    private final static long serialVersionUID = 4568533373035380225L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param visibility
     * @param timezone
     * @param main
     * @param clouds
     * @param sys
     * @param dt
     * @param coord
     * @param weather
     * @param name
     * @param cod
     * @param id
     * @param base
     * @param wind
     */
    public Data(Coord coord, List<Weather> weather, String base, Main main, int visibility, Wind wind, Clouds clouds, Sys sys, int id, String name) {
        super();
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        //this.dt = dt;
        this.sys = sys;
        //this.timezone = timezone;
        this.id = id;
        this.name = name;
        //this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Data withCoord(Coord coord) {
        this.coord = coord;
        return this;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Data withWeather(List<Weather> weather) {
        this.weather = weather;
        return this;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Data withBase(String base) {
        this.base = base;
        return this;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Data withMain(Main main) {
        this.main = main;
        return this;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Data withVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Data withWind(Wind wind) {
        this.wind = wind;
        return this;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Data withClouds(Clouds clouds) {
        this.clouds = clouds;
        return this;
    }

   /* public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Data withDt(int dt) {
        this.dt = dt;
        return this;
    }*/

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Data withSys(Sys sys) {
        this.sys = sys;
        return this;
    }

   /* public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public Data withTimezone(int timezone) {
        this.timezone = timezone;
        return this;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Data withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data withName(String name) {
        this.name = name;
        return this;
    }

/*    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Data withCod(int cod) {
        this.cod = cod;
        return this;
    }*/

    /*public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public Data withMessage(double message) {
        this.message = message;
        return this;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public Data withCnt(int cnt) {
        this.cnt = cnt;
        return this;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public Data withList(java.util.List<List> list) {
        this.list = list;
        return this;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Data withCity(City city) {
        this.city = city;
        return this;
    }*/

    public String informacionBasica(){
        final StringBuilder sb = new StringBuilder();
        sb.append("Ciudad: ").append(name).append('\n');
        sb.append("País: ").append(sys.getCountry()).append('\n');


        sb.append("Coordenadas ").append('\n');
        sb.append('\t').append("Latitud: ").append(coord.getLat()).append('\n');
        sb.append('\t').append("Longitud: ").append(coord.getLon()).append('\n');

        sb.append("Temperatura: ").append(main.getTemp()).append(" ºC").append('\n');

        sb.append("Lluvias: ").append(clouds.getAll()).append('%').append('\n');

        sb.append("Tiempo: ").append(weather.get(0));

        sb.append('\n');

        return sb.toString();
    }

    public String informacionDetallada(){
        final StringBuilder sb = new StringBuilder();
        sb.append("Ciudad: ").append(name).append('\n');
        sb.append("País: ").append(sys.getCountry()).append('\n');


        sb.append("Coordenadas ").append('\n');
        sb.append('\t').append("Latitud: ").append(coord.getLat()).append('\n');
        sb.append('\t').append("Longitud: ").append(coord.getLon()).append('\n');

        sb.append("Temperatura: ").append(main.getTemp()).append(" ºC").append('\n');
        sb.append("Temperatura mínima: ").append(main.getTempMin()).append(" ºC").append('\n');
        sb.append("Temperatura máxima: ").append(main.getTempMax()).append(" ºC").append('\n');

        sb.append("Lluvias: ").append(clouds.getAll()).append('%').append('\n');

        sb.append("Tiempo: ").append(weather.get(0));

        sb.append("Humedad: ").append(main.getHumidity()).append("%").append('\n');
        sb.append("Presión: ").append(main.getPressure()).append(" hpa").append('\n');

        sb.append("Viento ").append('\n');
        sb.append('\t').append("Velocidad: ").append(wind.getSpeed()).append(" m/s").append('\n');
        sb.append('\t').append("Grados: ").append(wind.getDeg()).append('\n');


        sb.append('\n');

        return sb.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Data{");
        sb.append("coord=").append(coord);
        sb.append(", weather=").append(weather);
        sb.append(", base='").append(base).append('\'');
        sb.append(", main=").append(main);
        sb.append(", visibility=").append(visibility);
        sb.append(", wind=").append(wind);
        sb.append(", clouds=").append(clouds);
        //sb.append(", dt=").append(dt);
        sb.append(", sys=").append(sys);
       // sb.append(", timezone=").append(timezone);
        sb.append(", id=").append(id);
        //sb.append(", name='").append(name).append('\'');
        //sb.append(", cod=").append(cod);
        sb.append('}');
        return sb.toString();
    }
}
