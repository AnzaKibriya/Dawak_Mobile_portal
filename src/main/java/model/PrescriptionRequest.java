package model;

import com.google.gson.annotations.SerializedName;

public class PrescriptionRequest {

    @SerializedName("patient")
    private Patient patient;

    @SerializedName("order")
    private Order order;

    // Constructors, getters, and setters

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
