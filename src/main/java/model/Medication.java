package model;

import com.google.gson.annotations.SerializedName;
public class Medication {
    @SerializedName("physicianOrderId")
    private String physicianOrderId;

    @SerializedName("ndcCode")
    private String ndcCode;

    @SerializedName("drugDescription")
    private String drugDescription;
    public String getPhysicianOrderId() {
        return physicianOrderId;
    }

    public void setPhysicianOrderId(String physicianOrderId) {
        this.physicianOrderId = physicianOrderId;
    }

    public String getNdcCode() {
        return ndcCode;
    }

    public void setNdcCode(String ndcCode) {
        this.ndcCode = ndcCode;
    }

    public String getDrugDescription() {
        return drugDescription;
    }

    public void setDrugDescription(String drugDescription) {
        this.drugDescription = drugDescription;
    }
}
