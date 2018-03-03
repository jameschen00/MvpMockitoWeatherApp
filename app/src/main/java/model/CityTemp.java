package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexk on 02/05/16.
 */
public class CityTemp {

    @SerializedName("temp")
    @Expose
    public Double temp;
    @SerializedName("pressure")
    @Expose
    public Double pressure;
    @SerializedName("humidity")
    @Expose
    public Integer humidity;
    @SerializedName("temp_min")
    @Expose
    public Double tempMin;
    @SerializedName("temp_max")
    @Expose
    public Double tempMax;
    @SerializedName("sea_level")
    @Expose
    public Double seaLevel;
    @SerializedName("grnd_level")
    @Expose
    public Double grndLevel;

    public double getTemp() {
        return temp;
    }
}
