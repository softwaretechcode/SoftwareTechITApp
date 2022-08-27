package com.softwaretechit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResourceId {


    @SerializedName("videoId")
    @Expose
    private String videoId;

    public ResourceId() {
    }

    public ResourceId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
