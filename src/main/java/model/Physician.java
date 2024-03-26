package model;

import com.google.gson.annotations.SerializedName;

public class Physician {
    @SerializedName("prescriber")
    private String prescriber;

    @SerializedName("licenseNo")
    private String licenseNo;
    public String getPrescriber() {
        return prescriber;
    }

    public void setPrescriber(String prescriber) {
        this.prescriber = prescriber;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
}

