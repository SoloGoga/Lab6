package sample;

import java.util.ArrayList;
import java.util.List;

public class WStats {
    public List<LondonWeather> weather = new ArrayList<LondonWeather>();

    public void add(LondonWeather item){
        weather.add(item);
        Main.guiController.addToTable(item);
    }

    public String toString()
    {
        String result = "";

        for(LondonWeather item : weather)
        {
            result += item.toString() + System.lineSeparator();
        }

        return result;
    }
}
