package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vrushali on 3/3/18.
 */

public class Wind {
    @SerializedName("speed")
    @Expose
    public Double speed;
    @SerializedName("deg")
    @Expose
    public double deg;

    public Wind(Double speed, double deg) {
        this.speed = speed;
        this.deg = deg;
    }
}
