package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vrushali on 3/3/18.
 */

public class Sys {

    @SerializedName("message")
    @Expose
    public Double message;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("sunrise")
    @Expose
    public Integer sunrise;
    @SerializedName("sunset")
    @Expose
    public Integer sunset;
}
