package model;


import com.google.gson.annotations.SerializedName;

public class DPClaimTask {
    @SerializedName("taskId")
    private int taskId;

    @SerializedName("id")
    private int id;

    // Getter and setter methods for each field

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
