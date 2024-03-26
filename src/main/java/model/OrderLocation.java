package model;

import com.google.gson.annotations.SerializedName;

public class OrderLocation {
    @SerializedName("code")
    private String code;

    @SerializedName("value")
    private String value;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
