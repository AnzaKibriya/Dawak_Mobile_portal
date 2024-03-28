package model;

import com.google.gson.annotations.SerializedName;

public class MedicationPaymentInfo {
    @SerializedName("comment")
    private String comment;

    @SerializedName("copay")
    private int copay;

    @SerializedName("healthPlan")
    private int healthPlan;

    @SerializedName("id")
    private int id;

    @SerializedName("medicationMasterId")
    private int medicationMasterId;

    @SerializedName("medicationRequestId")
    private int medicationRequestId;

    @SerializedName("prescriptionNumber")
    private String prescriptionNumber;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("rejectionReason")
    private String rejectionReason;

    @SerializedName("sigCodes")
    private String sigCodes;

    @SerializedName("sigCodesList")
    private String sigCodesList;

    // Getter and setter methods for each field

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCopay() {
        return copay;
    }

    public void setCopay(int copay) {
        this.copay = copay;
    }

    public int getHealthPlan() {
        return healthPlan;
    }

    public void setHealthPlan(int healthPlan) {
        this.healthPlan = healthPlan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicationMasterId() {
        return medicationMasterId;
    }

    public void setMedicationMasterId(int medicationMasterId) {
        this.medicationMasterId = medicationMasterId;
    }

    public int getMedicationRequestId() {
        return medicationRequestId;
    }

    public void setMedicationRequestId(int medicationRequestId) {
        this.medicationRequestId = medicationRequestId;
    }

    public String getPrescriptionNumber() {
        return prescriptionNumber;
    }

    public void setPrescriptionNumber(String prescriptionNumber) {
        this.prescriptionNumber = prescriptionNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getSigCodes() {
        return sigCodes;
    }

    public void setSigCodes(String sigCodes) {
        this.sigCodes = sigCodes;
    }

    public String getSigCodesList() {
        return sigCodesList;
    }

    public void setSigCodesList(String sigCodesList) {
        this.sigCodesList = sigCodesList;
    }
}
