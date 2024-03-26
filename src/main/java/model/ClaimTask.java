package model;

import com.google.gson.annotations.SerializedName;

public class ClaimTask {
    @SerializedName("userName")
    private String userName;

    @SerializedName("taskId")
    private String taskId;

    @SerializedName("encounterId")
    private String encounterId;

    @SerializedName("id")
    private int id;

    // Constructors, getters, and setters can be added as needed

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(String encounterId) {
        this.encounterId = encounterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
