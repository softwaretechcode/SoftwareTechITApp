package com.softwaretechit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelStatistic {
    @SerializedName("viewCount")
    @Expose
    private String viewCount;

    @SerializedName("subscriberCount")
    @Expose
    private String subscribeCount;

    @SerializedName("videoCount")
    @Expose
    private String videoCount;

    public ChannelStatistic() {
    }

    public ChannelStatistic(String viewCount, String subscribeCount, String videoCount) {
        this.viewCount = viewCount;
        this.subscribeCount = subscribeCount;
        this.videoCount = videoCount;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getSubscribeCount() {
        return subscribeCount;
    }

    public void setSubscribeCount(String subscribeCount) {
        this.subscribeCount = subscribeCount;
    }

    public String getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(String videoCount) {
        this.videoCount = videoCount;
    }
}
