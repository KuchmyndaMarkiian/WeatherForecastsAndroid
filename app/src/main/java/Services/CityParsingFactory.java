package Services;

import Abstractions.ParsingFactory;
import Models.City;
import Models.CityModel;

import java.util.*;

/**
 * Created by MARKAN on 28.06.2017.
 */
public class CityParsingFactory implements ParsingFactory<CityModel> {

    @Override
    public ArrayList<CityModel> parseText(String[] text) {
        if(text==null || text.length==0)
            return null;
        ArrayList<CityModel> cities=new ArrayList<>();
        for (String line:text) {
            String[] items=line.split("\t");
            if(items.length==5 && !Objects.equals(items[1], ""))
                cities.add(new CityModel(Integer.valueOf(items[0]),items[1],items[4]));
        }
        return cities;
    }
}
