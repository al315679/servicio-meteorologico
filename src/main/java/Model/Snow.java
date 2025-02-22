package Model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Snow implements Serializable
{

    @SerializedName("3h")
    @Expose
    private double _3h;
    private final static long serialVersionUID = -8867239319015229324L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Snow() {
    }

    /**
     * 
     * @param _3h
     */
    public Snow(double _3h) {
        super();
        this._3h = _3h;
    }

    public double get3h() {
        return _3h;
    }

    public void set3h(double _3h) {
        this._3h = _3h;
    }

    public Snow with3h(double _3h) {
        this._3h = _3h;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Snow{");
        sb.append("_3h=").append(_3h);
        sb.append('}');
        return sb.toString();
    }
}
