package Models;

/**
 * Created by MARKAN on 28.06.2017.
 */
public class CityModel {
    private int id;
    private String name;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    private String countryCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public CityModel() {
    }

    public CityModel(int id, String name, String countryCode) {
        this.id = id;
        this.name = name;
        this.countryCode=countryCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
