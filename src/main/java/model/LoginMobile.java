package model;

import com.google.gson.annotations.SerializedName;

public class LoginMobile {
    @SerializedName("userName")
    private String userName;

    @SerializedName("password")
    private String password;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @SerializedName("deviceToken")
    private String deviceToken;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
