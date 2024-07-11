package model;

import com.google.gson.annotations.SerializedName;
public class InsuranceCardVerification {
    @SerializedName("externalPrescriptionId")
    private int externalPrescriptionId;
    @SerializedName("fileType")
    private int fileType;

    @SerializedName("isVerify")
    private boolean isVerify;

    public int getExternalPrescriptionId() {
        return externalPrescriptionId;
    }

    public void setExternalPrescriptionId(int externalPrescriptionId) {
        this.externalPrescriptionId = externalPrescriptionId;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }
}
