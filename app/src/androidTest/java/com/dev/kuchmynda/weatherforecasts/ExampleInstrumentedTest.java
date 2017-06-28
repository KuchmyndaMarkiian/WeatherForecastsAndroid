package com.dev.kuchmynda.weatherforecasts;

import Abstractions.ParsingFactory;
import Services.CityParsingFactory;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void getCityList() throws Exception {
        String link="http://openweathermap.org/help/city_list.txt";
        OkHttpClient client=new OkHttpClient();
        Response response= client.newCall((new Request.Builder()).url(link).build()).execute();
        String body= response.body().string();
        String[] splited=body.substring(body.indexOf("\n")+1).split("\n");
        int length=splited.length;
        splited=Arrays.copyOf(splited,length);
        ParsingFactory factory=new CityParsingFactory();
        TreeSet set=factory.parseText(splited);
        assertTrue(response.isSuccessful() && set.size()>=1);
    }
}
