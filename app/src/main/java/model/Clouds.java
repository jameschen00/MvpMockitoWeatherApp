package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vrushali on 3/3/18.
 */

public class Clouds {
    @SerializedName("all")
    @Expose
    public Integer all;

    public Clouds(Integer all) {
        this.all = all;
    }
}
