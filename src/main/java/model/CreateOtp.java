package model;

import com.google.gson.annotations.SerializedName;

public class CreateOtp {
    @SerializedName("userName")
    private String userName;

    @SerializedName("isForgotPassword")
    private boolean isForgotPassword;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = userName;
    }

    public boolean isForgotPassword() {
        return isForgotPassword;
    }

    public void isForgotPassword(boolean isForgotPassword) {
        this.isForgotPassword = isForgotPassword;
    }
}
