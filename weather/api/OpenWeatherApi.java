package battleship.weather.api;
/**
 * This class connects to the openweathermap.org web server for retrieving
 * information about the weather.
 * Note: Do not call this class directly. Route all Weather API requests through
 * the WeatherApiTranslator class.
 * @author Andrew Braswell, Ike Quigley Last Updated: 11/13/2019
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import battleship.weather.util.Location;


public class OpenWeatherApi implements WeatherApiInterface {
    //Example url: https://api.openweathermap.org/data/2.5/weather?lat=28&lon=177&units=imperial&appid=ab85ba57bbbb423fb62bfb8201126ede

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?units=imperial";
    private static final String KEY = "ab85ba57bbbb423fb62bfb8201126ede";
    private JSONObject obj;

    @Override
    public void fetchWeatherByLocation (int _location) {
        int lat = Location.getLatitude(_location);
        int lon = Location.getLongitude(_location);

        String searchString = "&lat=" + lat + "&lon=" + lon + "&appid=" + OpenWeatherApi.KEY;

        try {
            URL url = new URL(OpenWeatherApi.BASE_URL + searchString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            // Build the content from the buffered input.
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            // Close the connections.
            in.close();
            con.disconnect();
            // Extract the JSON object.
            this.obj = new JSONObject(content.toString());
        } catch (Exception ex) {

        }
    }

    @Override
    public int loadWindDirection () {
        int windDirection;

        try {
        windDirection = this.obj.getJSONObject("wind").getInt("deg");
        } catch (Exception ex) {
            return -1;
        }

        return windDirection;
    }

    @Override
    public double loadWindSpeed () {
        int windSpeed;

        try {
        windSpeed = this.obj.getJSONObject("wind").getInt("speed");
        } catch (Exception ex) {
            return -1;
        }

        return windSpeed;

    }

    @Override
    public double loadTemperature () {
        double temperature;

        try {
        temperature = this.obj.getJSONObject("main").getDouble("temp");
        } catch (Exception ex) {
            return -1;
        }

        return temperature;
    }

}