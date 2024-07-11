package model;

import com.google.gson.annotations.SerializedName;

public class VerifyExternalMedication {
    @SerializedName("encounterId")
    private int encounterId;
    @SerializedName("isVerify")
    private boolean isVerify;
    @SerializedName("medicationCount")
    private int medicationCount;
    @SerializedName("prescriptionFileId")
    private int prescriptionFileId;

    public int getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(int encounterId) {
        this.encounterId = encounterId;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public int getMedicationCount() {
        return medicationCount;
    }

    public void setMedicationCount(int medicationCount) {
        this.medicationCount = medicationCount;
    }

    public int getPrescriptionFileId() {
        return prescriptionFileId;
    }

    public void setPrescriptionFileId(int prescriptionFileId) {
        this.prescriptionFileId = prescriptionFileId;
    }

}
