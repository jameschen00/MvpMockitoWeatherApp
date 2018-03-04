package presenter;

import app.leftshift.com.mvpmockitoweatherapp.BuildConfig;
import app.leftshift.com.mvpmockitoweatherapp.WeartherMapCallbackService;
import model.CityWeather;
import remote.WeatherMapService;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import view.Wheatherappview;

/**
 * Created by vrushali on 3/3/18.
 */

public class WeatherAppPresenter {

    WeatherMapService weatherMapService;
    CompositeSubscription compositeSubscription;
    Wheatherappview wheatherappview;

    WeartherMapCallbackService weartherMapCallbackService;
    private final String apiKey = BuildConfig.OPENWEATHERMAP_API_KEY;

    public WeatherAppPresenter(Wheatherappview wheatherappview,WeatherMapService apiInterface) {
        this.wheatherappview = wheatherappview;
        this.weatherMapService = apiInterface;
        compositeSubscription = new CompositeSubscription();
    }


    public void getWeatherInfo(String selectedcity) {
        Observable<CityWeather> cityWeatherObservable = weatherMapService.getWeatherByCityName(selectedcity, apiKey);
        Subscription weatherinfoSubscription  = cityWeatherObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CityWeather>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        wheatherappview.showServerError(e.getMessage());
                    }

                    @Override
                    public void onNext(CityWeather cityWeather) {
                        if (cityWeather != null) {
                            wheatherappview.showWeatherInfo(cityWeather);
                        }
                    }
                });
        compositeSubscription.add(weatherinfoSubscription);
    }


    //Alternate Api Call in presenter which handles only success and errors

    public void getWeatherInfoWithCallback(String selectedcity) {

        Subscription weathercallbackSubscription = null;
        weathercallbackSubscription = weartherMapCallbackService.getWeatherByCityName(selectedcity, apiKey,
                new WeartherMapCallbackService.CityWeatherCallback() {
                    @Override
                    public void onError(Throwable throwable) {
                        wheatherappview.showServerError(throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(CityWeather cityWeather) {
                        if (cityWeather != null) {
                            wheatherappview.showWeatherInfo(cityWeather);
                        }
                    }
                });

        compositeSubscription.add(weathercallbackSubscription);

    }
}
