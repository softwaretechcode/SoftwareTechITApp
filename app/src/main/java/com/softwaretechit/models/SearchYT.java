package com.softwaretechit.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchYT {
    @SerializedName("id")
    @Expose
    private ResourceId resourceId;

    @SerializedName("snippet")
     @Expose
    private SnippetYT SnippetYT;

    public SearchYT() {
    }

    public SearchYT(ResourceId resourceId) {
        this.resourceId = resourceId;
    }

    public SearchYT(com.softwaretechit.models.SnippetYT snippetYT) {
        SnippetYT = snippetYT;
    }

    public ResourceId getResourceId() {
        return resourceId;
    }

    public void setResourceId(ResourceId resourceId) {
        this.resourceId = resourceId;
    }

    public com.softwaretechit.models.SnippetYT getSnippetYT() {
        return SnippetYT;
    }

    public void setSnippetYT(com.softwaretechit.models.SnippetYT snippetYT) {
        SnippetYT = snippetYT;
    }
}
