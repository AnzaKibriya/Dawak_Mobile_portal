package model;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("addressText")
    private String addressText;

    @SerializedName("addressCity")
    private String addressCity;

    @SerializedName("area")
    private Area area;

    @SerializedName("state")
    private State state;

    @SerializedName("country")
    private Country country;


    public String getAddressText() {
        return addressText;
    }

    public void setAddressText(String addressText) {
        this.addressText = addressText;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
