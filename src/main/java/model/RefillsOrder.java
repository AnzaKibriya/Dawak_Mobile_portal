package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RefillsOrder {

    @SerializedName("parentFin")
    private String parentFin;

    @SerializedName("physicianEncounterId")
    private String physicianEncounterId;

    @SerializedName("physicianOrderDate")
    private String physicianOrderDate;

    @SerializedName("orderVisitDate")
    private String orderVisitDate;

    @SerializedName("orderLocation")
    private OrderLocation orderLocation;

    @SerializedName("physician")
    private Physician physician;

    @SerializedName("medications")
    private List<RefillMedication> medications;

    @SerializedName("orderType")
    private String orderType;

    @SerializedName("pharmacyEncounterId")
    private String pharmacyEncounterId;

    @SerializedName("workStation")
    private String workStation;

    @SerializedName("planName")
    private String planName;


    @SerializedName("encounterType")
    private EncounterType encounterType;

    @SerializedName("pharmacist")
    private RefillPharmacist pharmacist;

    public RefillPharmacist getRefillPharmacist() {
        return refillPharmacist;
    }



    public String getParentFin() {
        return parentFin;
    }

    public void setParentFin(String parentFin) {
        this.parentFin = parentFin;
    }

    public String getPhysicianEncounterId() {
        return physicianEncounterId;
    }

    public void setPhysicianEncounterId(String physicianEncounterId) {
        this.physicianEncounterId = physicianEncounterId;
    }

    public void setPharmacyEncounterId(String pharmacyEncounterId) {
        this.pharmacyEncounterId = pharmacyEncounterId;
    }
    public String getPhysicianOrderDate() {
        return physicianOrderDate;
    }

    public void setPhysicianOrderDate(String physicianOrderDate) {
        this.physicianOrderDate = physicianOrderDate;
    }

    public String getOrderVisitDate() {
        return orderVisitDate;
    }

    public void setOrderVisitDate(String orderVisitDate) {
        this.orderVisitDate = orderVisitDate;
    }

    public OrderLocation getOrderLocation() {
        return orderLocation;
    }

    public void setOrderLocation(OrderLocation orderLocation) {
        this.orderLocation = orderLocation;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public List<RefillMedication> getMedications() {
        return medications;
    }

    public void setMedications(List<RefillMedication> medications) {
        this.medications = medications;
    }

    public EncounterType getEncounterType() {
        return encounterType;
    }
    public void setEncounterType(EncounterType encounterType) {
        this.encounterType = encounterType;
    }
    public void setRefillPharmacist(RefillPharmacist refillPharmacist) {
        this.refillPharmacist = refillPharmacist;
    }

    @SerializedName("refillPharmacist")
    private RefillPharmacist refillPharmacist;

}




