package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexk on 02/05/16.
 */
public class Weather {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("main")
    @Expose
    public String main;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("icon")
    @Expose
    public String icon;

    public String getMain() {
        return main;
    }

    public int getId() {
        return id;
    }
}
