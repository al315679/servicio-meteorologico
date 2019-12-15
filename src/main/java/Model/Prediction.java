package Model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prediction implements Serializable
{

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list = null;
    @SerializedName("city")
    @Expose
    private City city;
    private final static long serialVersionUID = 8672558416954380666L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Prediction() {
    }

    /**
     * 
     * @param city
     * @param cnt
     * @param cod
     * @param message
     * @param list
     */
    public Prediction(String cod, double message, int cnt, java.util.List<List> list, City city) {
        super();
        this.cod = cod;
        this.message = message;
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Prediction withCod(String cod) {
        this.cod = cod;
        return this;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public Prediction withMessage(double message) {
        this.message = message;
        return this;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public Prediction withCnt(int cnt) {
        this.cnt = cnt;
        return this;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public Prediction withList(java.util.List<List> list) {
        this.list = list;
        return this;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Prediction withCity(City city) {
        this.city = city;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Prediction{");
        sb.append("cod='").append(cod).append('\'');
        sb.append(", message=").append(message);
        sb.append(", cnt=").append(cnt);
        sb.append(", list=").append(list);
        sb.append(", city=").append(city);
        sb.append('}');
        return sb.toString();
    }
}
