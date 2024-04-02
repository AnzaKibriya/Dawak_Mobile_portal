package model;

import com.google.gson.annotations.SerializedName;

public class ShipaEvent {
    @SerializedName("date")
    private String date;
    @SerializedName("event")
    private String event;
    @SerializedName("status")
    private String status;
    @SerializedName("shipaRef")
    private String shipaRef;
    @SerializedName("customerRef")
    private String customerRef;
    @SerializedName("storyStatusId")
    private String storyStatusId;

    @SerializedName("storyStatusText")
    private String storyStatusText;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShipaRef() {
        return shipaRef;
    }

    public void setShipaRef(String shipaRef) {
        this.shipaRef = shipaRef;
    }

    public String getCustomerRef() {
        return customerRef;
    }

    public void setCustomerRef(String customerRef) {
        this.customerRef = customerRef;
    }

    public String getStoryStatusId() {
        return storyStatusId;
    }

    public void setStoryStatusId(String storyStatusId) {
        this.storyStatusId = storyStatusId;
    }

    public String getStoryStatusText() {
        return storyStatusText;
    }

    public void setStoryStatusText(String storyStatusText) {
        this.storyStatusText = storyStatusText;
    }
}
