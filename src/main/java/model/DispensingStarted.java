package model;

import com.google.gson.annotations.SerializedName;

public class DispensingStarted {
    @SerializedName("encounterId")
    private String encounterId;

    @SerializedName("id")
    private int id;

    // Getter and setter methods for each field

    public String getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(String encounterId) {
        this.encounterId = encounterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
