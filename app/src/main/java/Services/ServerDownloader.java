package Services;

import Abstractions.ParsingFactory;
import Models.City;
import Models.CityModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by MARKAN on 28.06.2017.
 */
public final class ServerDownloader {
    public static ArrayList<CityModel> DownloadCities() throws IOException {
        String link = "http://openweathermap.org/help/city_list.txt";
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall((new Request.Builder()).url(link).build()).execute();
        String body = response.body().string();
        String[] splited = body.substring(body.indexOf("\n") + 1).split("\n");
        splited = Arrays.copyOf(splited, splited.length);
        ParsingFactory factory = new CityParsingFactory();
        return factory.parseText(splited);
    }
}
