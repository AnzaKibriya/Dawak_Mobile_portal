package model;

import com.google.gson.annotations.SerializedName;

public class ProceedExternalPrescription {
    @SerializedName("encounterId")
    private int encounterId;
    public int getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(int encounterId) {
        this.encounterId = encounterId;
    }
}
