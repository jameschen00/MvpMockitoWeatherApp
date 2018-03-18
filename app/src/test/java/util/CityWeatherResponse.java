package util;

import java.util.ArrayList;
import java.util.List;

import model.CityTemp;
import model.CityWeather;
import model.Clouds;
import model.Coord;
import model.Sys;
import model.Weather;
import model.Wind;

/**
 * Created by vrushali on 3/9/18.
 */

public class CityWeatherResponse {

    public static Coord getCoord() {
        Coord coord = new Coord(122.9, 233.9);
        return coord;
    }

    public static CityTemp getCityTemp() {
        CityTemp cityTemp = new CityTemp(12.32, 12.32, 33,
                12.32, 12.32, 12.32, 12.32);
        return cityTemp;
    }

    public static Wind getWind() {
        Wind wind = new Wind(12.33, 22.3);
        return wind;
    }

    public static Sys getSys() {
        Sys sys = new Sys(21.1, "ss", 2, 1);
        return sys;
    }

    public static Clouds getClouds() {
        Clouds clouds = new Clouds(122);
        return clouds;
    }

    public static List<Weather> getWeather() {
        List<Weather> weatherList = new ArrayList<>();
        Weather weather = new Weather(11, "description", "icon", "main");
        weatherList.add(0, weather);
        return weatherList;
    }
}
