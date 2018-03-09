package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vrushali on 3/3/18.
 */

public class Coord {
    @SerializedName("lon")
    @Expose
    public Double lon;
    @SerializedName("lat")
    @Expose
    public Double lat;

    public Coord(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }
}
