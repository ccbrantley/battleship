package battleship.weather.api;
/**
* This translator class acts as a central connector for all Weather API connections.
* Do not call any implemented Weather API classes directly. Route all
* requests through this central class.
*
* @author Andrew Braswell Last Updated: 11/13/2019
*/
public class WeatherApiTranslator implements WeatherApiInterface {
    protected static final WeatherApiInterface API = new OpenWeatherApi();

    @Override
    public void fetchWeatherByLocation (int _location) {
        WeatherApiTranslator.API.fetchWeatherByLocation(_location);
    }

    @Override
    public int loadWindDirection () {
        return WeatherApiTranslator.API.loadWindDirection();
    }

    @Override
    public double loadWindSpeed () {
        return WeatherApiTranslator.API.loadWindSpeed();
    }

    @Override
    public double loadTemperature () {
        return WeatherApiTranslator.API.loadTemperature();
    }
}
