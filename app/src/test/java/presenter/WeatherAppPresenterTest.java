package presenter;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import app.leftshift.com.mvpmockitoweatherapp.WeartherMapCallbackService;
import model.CityWeather;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.ResponseBody;
import remote.WeatherMapService;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscription;
import util.RxSchedulersOverrideRule;
import view.Wheatherappview;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by vrushali on 3/4/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherAppPresenterTest {

    @Mock
    Wheatherappview wheatherappview;

    @Mock
    WeatherMapService weatherMapService;

    @Mock
    WeartherMapCallbackService weartherMapCallbackService;

    private WeatherAppPresenter weatherAppPresenter;

    //TODO RX Schedular Rule - why,use
    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

    final String CONST_CITY_WEATHER_API_FILE = "city_weather_response.json";

    CityWeather cityWeatherResponse;
    Gson gson;

    //Define Subsciption  :- why, what is use
    public static final Subscription SUBSCRIPTION = new Subscription() {
        @Override
        public void unsubscribe() {

        }

        @Override
        public boolean isUnsubscribed() {
            return false;
        }
    };


    @Before
    public void setup() throws IOException {
        weatherAppPresenter = new WeatherAppPresenter(wheatherappview, weatherMapService);

       /* gson = new Gson();
        cityWeatherResponse = gson.fromJson(new WeatherAppResponseGenerator(
                "city_weather_response").readAll(), CityWeather.class);*/
    }

    @Test
    public void getWeatherInfoApiCall() throws Exception{

        //Given
        WeartherMapCallbackService.CityWeatherCallback cityWeatherCallback = Mockito.mock(WeartherMapCallbackService.CityWeatherCallback.class);
        weartherMapCallbackService = Mockito.mock(WeartherMapCallbackService.class);
        final CityWeather cityWeather = new CityWeather();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((WeartherMapCallbackService.CityWeatherCallback) invocation.getArguments()[0]).onSuccess(cityWeather);
                return SUBSCRIPTION;
            }
        }).when(weartherMapCallbackService.getWeatherByCityName("selectedcity",
                "appId",cityWeatherCallback));

        //when
        weatherAppPresenter.getWeatherInfoWithCallback(anyString());

        verify(wheatherappview).showWeatherInfo(cityWeather);
    }



    @Test
    public void getWeatherInfo()  {

        CityWeather cityWeather  = new CityWeather();
        String selectedcity = "selectedCity";
        String appId = "appId";
        Observable<CityWeather> cityWeatherObservable = Observable.just(cityWeather);

        when(weatherMapService.getWeatherByCityName(eq(selectedcity), eq(appId))).thenReturn(cityWeatherObservable);

        //When
        weatherAppPresenter.getWeatherInfo(selectedcity);

        //Then
        verify(wheatherappview).showWeatherInfo(cityWeather);
    }



    @Test
    public void getWeatherInfoAPiThrowsServerError()throws Exception{
        String selectedcity = "selectedCity";
        String appId = "appId";
        doReturn(Observable.error(generateWithStatusMessageAndStatusCode("error", 401))).
                when(weatherMapService.getWeatherByCityName(selectedcity,appId));

        weatherAppPresenter.getWeatherInfo(selectedcity);

        verify(wheatherappview).showServerError("error");

    }


    public static Throwable generateWithStatusMessageAndStatusCode(String value, int statusCode) {
        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();
        return new HttpException(
                Response.error(
                        ResponseBody.create(MediaType.parse("application/json"),
                                String.format("{\"statusMessage\":\"%s\"}", value)),
                        new okhttp3.Response.Builder()
                                .request(request).protocol(Protocol.HTTP_2)
                                .code(statusCode)
                                .addHeader("Error-Message", value).build()
                )
        );
    }
}