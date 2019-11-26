package battleship.weather.weatherapp;
/**
 * This class is for demonstrating the use of a weather api.
 *
 * @author Andrew Braswell Last Updated: 11/25/2019
 */
import java.text.DecimalFormat;

import battleship.weather.models.Weather;


public class WeatherApp {
    public static void main(String[] args) {
        DecimalFormat f = new DecimalFormat("#.##");

        for (Weather w : Weather.loadWeathersForAllLocations()) {
            System.out.println("The temperature in " + w.getLocationName() + " is " + w.getTemperature() + " F.");
            System.out.println("Windspeed: " + w.getWindSpeed() + ".  Wind direction: " + w.getWindDirection());
            System.out.println("The wind is blowing " + f.format(w.getXWindSpeed()) + " mph from the west and " + f.format(w.getYWindSpeed()) + " mph from the north. \n");
        }
    }
}
