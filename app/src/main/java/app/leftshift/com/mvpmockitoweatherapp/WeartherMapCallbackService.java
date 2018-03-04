package app.leftshift.com.mvpmockitoweatherapp;

import model.CityWeather;
import remote.WeatherMapService;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vrushali on 3/4/18.
 */

public class WeartherMapCallbackService {


    private WeatherMapService apiInterface;

    public WeartherMapCallbackService(WeatherMapService apiInterface) {

        this.apiInterface = apiInterface;
    }

    public Subscription getWeatherByCityName(String selectedcity, String apiKey,
                                             final CityWeatherCallback cityWeatherCallback) {
        return apiInterface.getWeatherByCityName(selectedcity, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CityWeather>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        cityWeatherCallback.onError(e);
                    }

                    @Override
                    public void onNext(CityWeather cityWeather) {
                        cityWeatherCallback.onSuccess(cityWeather);
                    }
                });
    }

    public interface CityWeatherCallback {
        void onSuccess(CityWeather cityWeather);

        void onError(Throwable var1);
    }

}
