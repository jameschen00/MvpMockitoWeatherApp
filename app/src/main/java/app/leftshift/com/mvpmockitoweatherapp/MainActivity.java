package app.leftshift.com.mvpmockitoweatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import model.CityWeather;
import presenter.WeatherAppPresenter;
import remote.Interceptor;
import remote.ItemOffsetDecoration;
import remote.WeatherMapService;
import view.Wheatherappview;


public class MainActivity extends AppCompatActivity implements Wheatherappview {


    private EditText selectCity;
    private RecyclerView showInfoRecyclerView;
    private ImageView imageViewSearch;
    private RelativeLayout relativeWeatherInfoLayout;
    WeatherAppPresenter weatherAppPresenter;
    WeatherMapService apiInterface;
    CityWeatherAdapter cityWeatherAdapter;

    String apiKey = BuildConfig.OPENWEATHERMAP_API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = Interceptor.getClient().create(WeatherMapService.class);

        initviews();
    }

    private void initviews() {
        selectCity = findViewById(R.id.edittext_city_name);
        showInfoRecyclerView = findViewById(R.id.city_recyclerview);
        imageViewSearch = findViewById(R.id.image_search);
        relativeWeatherInfoLayout = findViewById(R.id.relativelayout_weatherinfo);

        weatherAppPresenter = new WeatherAppPresenter(this,apiInterface);

        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cityName = selectCity.getText().toString();

                weatherAppPresenter.getWeatherInfoByCity(cityName,apiKey);

            }
        });
    }


    private void setAdapter(CityWeather cityWeather) {
        cityWeatherAdapter = new CityWeatherAdapter(this, cityWeather);
        showInfoRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        showInfoRecyclerView.addItemDecoration(itemOffsetDecoration);
        showInfoRecyclerView.setAdapter(cityWeatherAdapter);
    }

    @Override
    public void showServerError(String message) {
        BaseUtils.showSnackbar(relativeWeatherInfoLayout,message);
    }

    @Override
    public void showWeatherInfo(CityWeather cityWeather) {
        hideKeyboard();
        setAdapter(cityWeather);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)this.getSystemService("input_method");
        imm.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(), 0);
    }

}
