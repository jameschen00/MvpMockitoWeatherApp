package view;

import model.CityWeather;

/**
 * Created by vrushali on 3/3/18.
 */

public interface Wheatherappview {
    void showServerError(String message);

    void showWeatherInfo(CityWeather cityWeather);
}
