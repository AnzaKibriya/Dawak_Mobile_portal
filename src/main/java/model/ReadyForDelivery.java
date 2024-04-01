package model;

import com.google.gson.annotations.SerializedName;

public class ReadyForDelivery {
    @SerializedName("encounterId")
    private String encounterId;

    @SerializedName("taskId")
    private String taskId;

    @SerializedName("id")
    private int id;

    // Getter and setter methods for each field

    public String getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(String encounterId) {
        this.encounterId = encounterId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
