package model;

import com.google.gson.annotations.SerializedName;

public class ConfirmInsurance {
    @SerializedName("id")
    private int id;

    @SerializedName("prescriptionNumber")
    private String prescriptionNumber;

    @SerializedName("taskId")
    private String taskId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrescriptionNumber() {
        return prescriptionNumber;
    }

    public void setPrescriptionNumber(String prescriptionNumber) {
        this.prescriptionNumber = prescriptionNumber;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


}
