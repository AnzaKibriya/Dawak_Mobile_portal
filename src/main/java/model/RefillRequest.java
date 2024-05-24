package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RefillRequest {

    @SerializedName("patient")
    private Patient patient;

    @SerializedName("order")
    private RefillsOrder refillsOrder;

    @SerializedName("refillRequest")
    private RefillRequest refillRequest;

    @SerializedName("encounterType")
    private EncounterType encounterType;

    @SerializedName("orderLocation")
    private OrderLocation orderLocation;

    @SerializedName("physician")
    private Physician physician;

    @SerializedName("refillPharmacist")
    private RefillPharmacist refillPharmacist;

    @SerializedName("medications")
    private List<RefillMedication> medications;

    @SerializedName("refillInsurance")
    private RefillInsurance refillInsurance;

    @SerializedName("RefillQuantity")
    private RefillQuantity refillQuantity;

    @SerializedName("orderDiagnosis")
    private OrderDiagnosis orderDiagnosis;

    @SerializedName("drugAdministrationRoute")
    private DrugAdministrationRoute drugAdministrationRoute;




    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public RefillsOrder getRefillsOrder() {
        return refillsOrder;
    }

    public void setRefillsOrder(RefillsOrder refillsOrder) {
        this.refillsOrder = refillsOrder;
    }

    public RefillRequest getRefillRequest() {
        return refillRequest;
    }

    public void setRefillRequest(RefillRequest refillRequest) {
        this.refillRequest = refillRequest;
    }

    public EncounterType getEncounterType() {
        return encounterType;
    }

    public void setEncounterType(EncounterType encounterType) {
        this.encounterType = encounterType;
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

    public RefillPharmacist getRefillPharmacist() {
        return refillPharmacist;
    }

    public void setRefillPharmacist(RefillPharmacist refillPharmacist) {
        this.refillPharmacist = refillPharmacist;
    }

    public List<RefillMedication> getMedicine() {
        return medications;
    }

    public void setMedicine(List<RefillMedication> medications) {
        this.medications = medications;
    }

    public RefillInsurance getRefillInsurance() {
        return refillInsurance;
    }

    public void setRefillInsurance(RefillInsurance refillInsurance) {
        this.refillInsurance = refillInsurance;
    }

    public RefillQuantity getRefillQuantity() {
        return refillQuantity;
    }

    public void setRefillQuantity(RefillQuantity refillQuantity) {
        this.refillQuantity = refillQuantity;
    }

    public OrderDiagnosis getOrderDiagnosis() {
        return orderDiagnosis;
    }

    public void setOrderDiagnosis(OrderDiagnosis orderDiagnosis) {
        this.orderDiagnosis = orderDiagnosis;
    }

    public DrugAdministrationRoute getDrugAdministrationRoute() {
        return drugAdministrationRoute;
    }

    public void setDrugAdministrationRoute(DrugAdministrationRoute drugAdministrationRoute) {
        this.drugAdministrationRoute = drugAdministrationRoute;
    }






    // Constructors, getters, and setters


}
