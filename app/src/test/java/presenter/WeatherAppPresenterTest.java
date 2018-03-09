package presenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import model.CityWeather;
import remote.WeatherMapService;
import rx.Observable;
import util.RxSchedulersOverrideRule;
import view.Wheatherappview;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static util.CityWeatherResponse.getCityTemp;
import static util.CityWeatherResponse.getClouds;
import static util.CityWeatherResponse.getCoord;
import static util.CityWeatherResponse.getSys;
import static util.CityWeatherResponse.getWeather;
import static util.CityWeatherResponse.getWind;
import static util.ErrorResponseGenerator.generateWithStatusMessageAndStatusCode;

/**
 * Created by vrushali on 3/4/18.
 */

/*
    JUnit Runner provided by Mockito, Helps in initailising the Mock's
 */

@RunWith(MockitoJUnitRunner.class)
public class WeatherAppPresenterTest {

    @Mock
    Wheatherappview wheatherappview;

    @Mock
    WeatherMapService weatherMapService;

    private WeatherAppPresenter weatherAppPresenter;


    private CityWeather cityWeather;

    // RX Schedular Rule - why,use -
    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    @Before
    public void setup() throws IOException {
        weatherAppPresenter = new WeatherAppPresenter(wheatherappview,
                weatherMapService);
    }

    @Test
    public void testShouldVerifyIfGetWeatherInfoByCityIsCalled()  {

        String selectedcity =  "Pune";
        String appId = "AppId";

        //Given
        CityWeather cityWeather  = new CityWeather(getCoord(), getWeather(), "base", getCityTemp(),
                                    getWind(), getClouds(), 11, getSys(), 33, "name", 33);

        Observable<CityWeather> cityWeatherObservable = Observable.just(cityWeather);

        when(weatherMapService.getWeatherByCityName(eq(selectedcity), eq(appId))).thenReturn(cityWeatherObservable);

       //When
        weatherAppPresenter.getWeatherInfoByCity(selectedcity, appId);


        //Then
        verify(wheatherappview).showWeatherInfo(cityWeather);
    }
    @Test
    public void getWeatherInfoAPiThrowsServerError() throws Exception {

        //Given

        doReturn(Observable.error(generateWithStatusMessageAndStatusCode(
                "HTTP 402 null", 402))).
                when(weatherMapService).getWeatherByCityName(anyString(), anyString());


        //When
        weatherAppPresenter.getWeatherInfoByCity("Pune", "AppId");

        //Then
        verify(wheatherappview).showServerError("HTTP 402 null");

    }
}