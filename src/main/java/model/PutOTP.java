package model;

import com.google.gson.annotations.SerializedName;

public class PutOTP {
    @SerializedName("userName")
    private String userName;

    @SerializedName("otp")
    private String otp;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String password) {
        this.otp = otp;
    }
}
