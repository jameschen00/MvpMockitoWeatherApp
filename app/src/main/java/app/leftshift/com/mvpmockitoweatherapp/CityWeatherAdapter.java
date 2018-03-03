package app.leftshift.com.mvpmockitoweatherapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import model.CityWeather;

/**
 * Created by vrushali on 3/3/18.
 */

public class CityWeatherAdapter extends RecyclerView.Adapter<CityWeatherAdapter.CityWeatherViewHolder> {


    CityWeather cityWeather;
    private Context context;

    public CityWeatherAdapter(Context context, CityWeather cityWeather) {
        this.context = context;
        this.cityWeather = cityWeather;
    }
    @Override
    public CityWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_weather_card, null);
        CityWeatherViewHolder viewHolder = new CityWeatherViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CityWeatherViewHolder holder, int position) {

        holder.cityNameTextView.setText(cityWeather.getName());

        String weatherInCelsius = " - ";

        if (cityWeather.getMain() != null) {
            weatherInCelsius = BaseUtils.getDegreesRepresentation(context,
                    BaseUtils.convertToCelsius(cityWeather.getMain().getTemp()));
        }

        holder.cityWeatherDegreesTextView.setText(weatherInCelsius);
        holder.cityWeatherDescriptionTextView.setText(cityWeather.getWeather().get(0).getMain());
        holder.cityWeatherIconImageView.setImageResource(BaseUtils
                .getArtResourceForWeatherCondition(cityWeather.getWeather().get(0).getId()));
        holder.cityWeatherFavoriteImageView.setImageResource(R.drawable.ic_favorite_border_white_36dp);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class CityWeatherViewHolder extends RecyclerView.ViewHolder {

        protected TextView cityNameTextView;
        protected TextView cityWeatherDegreesTextView;
        protected TextView cityWeatherDescriptionTextView;
        protected  ImageView cityWeatherIconImageView;
        protected ImageView cityWeatherFavoriteImageView;


        public CityWeatherViewHolder(View itemView) {
            super(itemView);
            this.cityNameTextView = itemView.findViewById(R.id.city_name_textview);
            this.cityWeatherDegreesTextView = itemView.findViewById(R.id.city_weather_degrees_textview);
            this.cityWeatherDescriptionTextView = itemView.findViewById(R.id.city_weather_description_textview);
            this.cityWeatherIconImageView = itemView.findViewById(R.id.city_weather_icon_imageview);
            this.cityWeatherFavoriteImageView = itemView.findViewById(R.id.city_weather_favorite_imageview);
        }
    }
}

