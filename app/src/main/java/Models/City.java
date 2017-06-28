package Models;

import java.io.Serializable;

/**
 * Created by MARKAN on 28.06.2017.
 */
public class City extends CityModel implements Serializable {
    //region Fields
    private int id;
    private float latitude;
    private float longtitude;

    public City(int id, String name, float latitude, float longtitude, String countryCode) {
        super(id, name,countryCode);
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public City(String[] items) {
        this(Integer.valueOf(items[0]), items[1], Float.valueOf(items[2]), Float.valueOf(items[3]), items[4]);
    }

    //endregion


    //region Properties(Getters & Setters)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }


    //endregion


}
