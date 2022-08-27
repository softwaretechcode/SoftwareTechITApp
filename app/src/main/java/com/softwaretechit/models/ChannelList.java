package com.softwaretechit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelList {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("snippet")
    @Expose
    private SnippetYT snippet;

    @SerializedName("statistics")
    @Expose
    private ChannelStatistic statistics;
    @SerializedName("brandingSettings")
    @Expose
    private ChannelBranding brandingSettings;

    public ChannelList() {
    }

    public ChannelList(String id, SnippetYT snippet, ChannelStatistic statistics, ChannelBranding brandingSettings) {
        this.id = id;
        this.snippet = snippet;
        this.statistics = statistics;
        this.brandingSettings = brandingSettings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SnippetYT getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetYT snippet) {
        this.snippet = snippet;
    }

    public ChannelStatistic getStatistics() {
        return statistics;
    }

    public void setStatistics(ChannelStatistic statistics) {
        this.statistics = statistics;
    }

    public ChannelBranding getBrandingSettings() {
        return brandingSettings;
    }

    public void setBrandingSettings(ChannelBranding brandingSettings) {
        this.brandingSettings = brandingSettings;
    }
}
