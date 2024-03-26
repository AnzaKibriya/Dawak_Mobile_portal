package model;

import com.google.gson.annotations.SerializedName;

public class SendForInsurance {
    @SerializedName("encounterId")
    private String encounterId;

    @SerializedName("id")
    private int id;

    // Constructors, getters, and setters can be added as needed

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
