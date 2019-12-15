package Model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys implements Serializable
{


    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("pod")
    @Expose
    private String pod;
    private final static long serialVersionUID = -7499129837514828899L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sys() {
    }

    /**
     * 
     * @param country
     * @param pod
     */
    public Sys(String country, String pod) {
        super();
        this.country = country;

    }




    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Sys withCountry(String country) {
        this.country = country;
        return this;
    }

    public Sys(String pod) {
        super();
        this.pod = pod;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public Sys withPod(String pod) {
        this.pod = pod;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sys{");
        sb.append("country='").append(country).append('\'');
        return sb.toString();
    }
}
