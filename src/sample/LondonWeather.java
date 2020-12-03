package sample;

import java.util.Comparator;

public class LondonWeather {

    private final String weatherStateName;
    private final String created;
    private final double minTemp;
    private final double maxTemp;
    private final double theTemp;
    private final double windSpeed;
    private final double airPressure;

    public LondonWeather(String weatherStateName, String created, double minTemp, double maxTemp, double theTemp, double windSpeed, double airPressure) {
        super();
        this.weatherStateName = weatherStateName;
        this.created = created;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.theTemp = theTemp;
        this.windSpeed = windSpeed;
        this.airPressure = airPressure;
    }

    public String getWeatherStateName() {
        return weatherStateName;
    }

    public String getCreated() {
        return created;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getTheTemp() {
        return theTemp;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getAirPressure() {
        return airPressure;
    }

    @Override
    public String toString() {
        return "Weather: " + weatherStateName + ", " +
                "date and time: " + created + ", " +
                "minimal temperature: " + minTemp + ", " +
                "maximal temperature: " + maxTemp + ", " +
                "current temperature: " + theTemp + ", " +
                "wind speed: " + windSpeed + ", " +
                "air pressure: " + airPressure + ";" + System.lineSeparator();
    }

    public static Comparator<LondonWeather> byNameWeatherAsc = Comparator.comparing(o -> o.weatherStateName);
    public static Comparator<LondonWeather> byNameWeatherDesc = (o1, o2) -> o2.weatherStateName.compareTo(o1.weatherStateName);
    public static Comparator<LondonWeather> byTheTempAsc = ((o1, o2) -> o1.theTemp > o2.theTemp ? 1 : o1.theTemp < o2.theTemp ? -1 : 0);
    public static Comparator<LondonWeather> byTheTempDesc = ((o1, o2) -> o1.theTemp < o2.theTemp ? 1 : o1.theTemp > o2.theTemp ? -1 : 0);
}
