package model;

import com.google.gson.annotations.SerializedName;
public class AddExternalMedication {
    @SerializedName("comment")
    private String comment;

    @SerializedName("copay")
    private int copay;

    @SerializedName("daysSupply")
    private int daysSupply;

    @SerializedName("healthPlan")
    private int healthPlan;

    @SerializedName("id")
    private String id;

    @SerializedName("medicationMasterId")
    private int medicationMasterId;

    @SerializedName("prescribedBy")
    private String prescribedBy;

    @SerializedName("prescriptionNumber")
    private String prescriptionNumber;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("quantityRemaining")
    private int quantityRemaining;

    @SerializedName("refillQuantity")
    private int refillQuantity;

    @SerializedName("refillRemaining")
    private int refillRemaining;

    @SerializedName("rejectionReason")
    private String rejectionReason;

    @SerializedName("sigCodes")
    private String sigCodes;

    @SerializedName("sigCodesList")
    private String sigCodesList;

    // Getters and Setters
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

    public int getDaysSupply() {
        return daysSupply;
    }

    public void setDaysSupply(int daysSupply) {
        this.daysSupply = daysSupply;
    }

    public int getHealthPlan() {
        return healthPlan;
    }

    public void setHealthPlan(int healthPlan) {
        this.healthPlan = healthPlan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMedicationMasterId() {
        return medicationMasterId;
    }

    public void setMedicationMasterId(int medicationMasterId) {
        this.medicationMasterId = medicationMasterId;
    }

    public String getPrescribedBy() {
        return prescribedBy;
    }

    public void setPrescribedBy(String prescribedBy) {
        this.prescribedBy = prescribedBy;
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

    public int getQuantityRemaining() {
        return quantityRemaining;
    }

    public void setQuantityRemaining(int quantityRemaining) {
        this.quantityRemaining = quantityRemaining;
    }

    public int getRefillQuantity() {
        return refillQuantity;
    }

    public void setRefillQuantity(int refillQuantity) {
        this.refillQuantity = refillQuantity;
    }

    public int getRefillRemaining() {
        return refillRemaining;
    }

    public void setRefillRemaining(int refillRemaining) {
        this.refillRemaining = refillRemaining;
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
