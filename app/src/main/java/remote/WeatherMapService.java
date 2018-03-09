package remote;

import model.CityWeather;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by vrushali on 3/3/18.
 */

public interface WeatherMapService {

    @GET("weather")
    Observable<CityWeather> getWeatherByCityName(@Query("q") String cityName, @Query("appid") String appId);

}
