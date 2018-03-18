package presenter;

import org.junit.After;
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
import static org.mockito.Mockito.validateMockitoUsage;
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

    /*
        RxSchedulersOverrideRule will help to create - the stubs of the
        Not interact with actual RX java methods
     */

    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    @Before
    public void setup() throws IOException {
        weatherAppPresenter = new WeatherAppPresenter(wheatherappview,
                weatherMapService);
    }


    /* Objective :-
        1. Ensure getWeatherByCityName is called or Not
        2. Verify using interface Method
       */


    @Test
    public void testShouldVerifyIfGetWeatherInfoByCityIsCalled()  {
        //Given
        String city =  "Pune";
        String appId = "AppId";

        CityWeather cityWeather  = new CityWeather(getCoord(), getWeather(), "base", getCityTemp(),
                                    getWind(), getClouds(), 11, getSys(), 33, "name", 33);

        Observable<CityWeather> cityWeatherObservable = Observable.just(cityWeather);

        when(weatherMapService.getWeatherByCityName(eq(city), eq(appId))).
                thenReturn(cityWeatherObservable);

       //When
        weatherAppPresenter.getWeatherInfoByCity(city, appId);


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

    @After
    public void tearDown(){
        /*
            1.  runner would do for you automatically
            2.  help determine whether you've misused matchers.
         */
        validateMockitoUsage();
    }
}