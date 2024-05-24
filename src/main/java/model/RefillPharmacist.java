package model;

import com.google.gson.annotations.SerializedName;

public class RefillPharmacist {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @SerializedName("username")
    private String username;

    @SerializedName("fullName")
    private String fullName;

}
