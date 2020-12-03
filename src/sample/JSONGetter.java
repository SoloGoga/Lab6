package sample;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class JSONGetter extends Thread {
    private List<String> w = new ArrayList<String>();

    public boolean checkDatForExistence(String year, String month, String day)
    {

        int y;
        int m;
        int d;
        int[] months = {4, 6, 9, 11};

        try{
            y = Integer.parseInt(year);
            m = Integer.parseInt(month);
            d = Integer.parseInt(day);
            if(!(y > 2013 && y < 2021 && m > 0 && m < 13 && d > 0 && d < 32))
                return false;
        }
        catch (Exception e){
            return false;
        }

        for(int i = 0; i < months.length; i++) {
            if (m == months[i] && d > 30)
                return false;
        }

        if(m == 2 && y != 2016 || y != 2020 &&  d > 28)
            return false;


        String str = year + '/' + month + '/' + day;
        w.add(str);

        return true;
    }

    public void stopSearch()
    {
        w.add(";");
    }

    public String connectAndGetData(String url)
    {
        String json = "";
        InputStream input;
        try
        {
            input = new URL(url).openStream();
            try
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
                try
                {
                    StringBuilder sb = new StringBuilder();
                    int cp;
                    while ((cp = reader.read()) != -1)
                    {
                        sb.append((char) cp);
                    }
                    json = sb.toString();
                }
                catch (IOException e)
                {
                }
            }
            finally
            {
                try
                {
                    input.close();
                }
                catch (IOException e)
                {
                }
            }
        }
        catch(IOException e)
        {
        }
        return json;
    }

    private void processJSONData(String jsonString)
    {
        Object obj = null;
        try
        {
            obj = new JSONParser().parse(jsonString);
        }
        catch (ParseException e)
        {
        }

        JSONArray jsonArray = (JSONArray) obj;
        for(Object jsonObject : jsonArray){
            JSONObject current = (JSONObject) jsonObject;
            String weatherStateName = (String) current.get("weather_state_name");
            String created = (String) current.get("created");
            double minTemp = (double) current.get("min_temp");
            double maxTemp = (double) current.get("max_temp");
            double theTemp = (double) current.get("the_temp");
            double windSpeed = (double) current.get("wind_speed");
            double airPressure = (double) current.get("air_pressure");
            LondonWeather lnWeather = new LondonWeather(weatherStateName, created, minTemp, maxTemp,
                    theTemp, windSpeed, airPressure);

            Main.weatherStats.add(lnWeather);
        }
    }

    @Override
    public void run() {
        super.run();

        while(true)
        {
            if(w.isEmpty())
            {
                try { sleep(200); }
                catch(InterruptedException e) {}
                continue;
            }
            if(w.get(0) == ";")
            {
                w.remove(0);
                break;
            }

            String url = "https://www.metaweather.com/api/location/44418/" + w.get(0);
            String json = connectAndGetData(url);
            processJSONData(json);
            w.remove(0);
        }
    }
}
