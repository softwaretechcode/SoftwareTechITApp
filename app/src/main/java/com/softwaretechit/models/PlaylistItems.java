package com.softwaretechit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaylistItems {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("snippet")
    @Expose
    private PlaylistSnippet snippet;

    @SerializedName("contentDetails")
    @Expose
    private  PlaylistDetails contentDetails;

    @SerializedName("status")
    @Expose
    private PlaylistStatus status;

    public PlaylistItems() {
    }

    public PlaylistItems(String id, PlaylistSnippet snippet, PlaylistDetails contentDetails,PlaylistStatus status) {
        this.id = id;
        this.snippet = snippet;
        this.contentDetails = contentDetails;
        this.status=status;
    }

    public PlaylistStatus getStatus() {
        return status;
    }

    public void setStatus(PlaylistStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlaylistSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(PlaylistSnippet snippet) {
        this.snippet = snippet;
    }

    public PlaylistDetails getContentDetails() {
        return contentDetails;
    }

    public void setContentDetails(PlaylistDetails contentDetails) {
        this.contentDetails = contentDetails;
    }
}
