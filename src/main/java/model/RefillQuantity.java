package model;

import com.google.gson.annotations.SerializedName;

public class RefillQuantity {

    public String getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(String currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public String getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(String remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getRemainingCycles() {
        return remainingCycles;
    }

    public void setRemainingCycles(String remainingCycles) {
        this.remainingCycles = remainingCycles;
    }

    public String getRxaDaysSupply() {
        return rxaDaysSupply;
    }

    public void setRxaDaysSupply(String rxaDaysSupply) {
        this.rxaDaysSupply = rxaDaysSupply;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    @SerializedName("currentQuantity")
    private String currentQuantity;

    @SerializedName("remainingQuantity")
    private String remainingQuantity;

    @SerializedName("totalQuantity")
    private String totalQuantity;

    @SerializedName("remainingCycles")
    private String remainingCycles;

    @SerializedName("rxaDaysSupply")
    private String rxaDaysSupply;

    @SerializedName("uom")
    private String uom;

}
