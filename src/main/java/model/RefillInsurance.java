package model;

import com.google.gson.annotations.SerializedName;

public class RefillInsurance {

    public String getSelfPayAmount() {
        return selfPayAmount;
    }

    public void setSelfPayAmount(String selfPayAmount) {
        this.selfPayAmount = selfPayAmount;
    }

    public String getCopayAmount() {
        return copayAmount;
    }

    public void setCopayAmount(String copayAmount) {
        this.copayAmount = copayAmount;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("status")
    private String status;
    @SerializedName("statusReason")
    private String statusReason;
    @SerializedName("copayAmount")
    private String copayAmount;
    @SerializedName("selfPayAmount")
    private String selfPayAmount;

}
