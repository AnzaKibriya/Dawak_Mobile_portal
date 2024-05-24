package model;

import com.google.gson.annotations.SerializedName;

public class RefillMedication {
    @SerializedName("physicianOrderId")
    private String physicianOrderId;

    @SerializedName("ndcCode")
    private String ndcCode;

    @SerializedName("drugDescription")
    private String drugDescription;
    @SerializedName("dispensedOrderId")
    private String dispensedOrderId;

    @SerializedName("nextRefillDate")
    private String nextRefillDate;

    @SerializedName("lastDispDate")
    private String lastDispDate;

    @SerializedName("dohCode")
    private String dohCode;

    @SerializedName("brandName")
    private String brandName;

    @SerializedName("manfName")
    private String manfName;

    @SerializedName("legalStatus")
    private String legalStatus;

    @SerializedName("drugForm")
    private String drugForm;

    @SerializedName("phxNbr")
    private String phxNbr;

    @SerializedName("orderDetailSimple")
    private String orderDetailSimple;

    @SerializedName("orderDetailDtl")
    private String orderDetailDtl;


    @SerializedName("insurance")
    private RefillInsurance insurance;

    @SerializedName("quantity")
    private RefillQuantity quantity;

    @SerializedName("orderDiagnosis")
    private OrderDiagnosis orderDiagnosis;

    @SerializedName("drugAdministrationRoute")
    private DrugAdministrationRoute drugAdministrationRoute;

    public String getDispensedOrderId() {
        return dispensedOrderId;
    }

    public void setDispensedOrderId(String dispensedOrderId) {
        this.dispensedOrderId = dispensedOrderId;
    }

    public String getNextRefillDate() {
        return nextRefillDate;
    }

    public void setNextRefillDate(String nextRefillDate) {
        this.nextRefillDate = nextRefillDate;
    }

    public String getLastDispDate() {
        return lastDispDate;
    }

    public void setLastDispDate(String lastDispDate) {
        this.lastDispDate = lastDispDate;
    }

    public String getDohCode() {
        return dohCode;
    }

    public void setDohCode(String dohCode) {
        this.dohCode = dohCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getManfName() {
        return manfName;
    }

    public void setManfName(String manfName) {
        this.manfName = manfName;
    }

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
    public String getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(String legalStatus) {
        this.legalStatus = legalStatus;
    }

    public String getDrugForm() {
        return drugForm;
    }

    public void setDrugForm(String drugForm) {
        this.drugForm = drugForm;
    }

    public String getPhxNbr() {
        return phxNbr;
    }

    public void setPhxNbr(String phxNbr) {
        this.phxNbr = phxNbr;
    }

    public String getOrderDetailSimple() {
        return orderDetailSimple;
    }

    public void setOrderDetailSimple(String orderDetailSimple) {
        this.orderDetailSimple = orderDetailSimple;
    }

    public String getOrderDetailDtl() {
        return orderDetailDtl;
    }

    public void setOrderDetailDtl(String orderDetailDtl) {
        this.orderDetailDtl = orderDetailDtl;
    }

}
