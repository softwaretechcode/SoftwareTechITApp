package com.softwaretechit.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoYT {

    @SerializedName("snippet")
     @Expose
    private SnippetYT SnippetYT;

    public VideoYT() {
    }

    public VideoYT(com.softwaretechit.models.SnippetYT snippetYT) {
        SnippetYT = snippetYT;
    }


    public com.softwaretechit.models.SnippetYT getSnippetYT() {
        return SnippetYT;
    }

    public void setSnippetYT(com.softwaretechit.models.SnippetYT snippetYT) {
        SnippetYT = snippetYT;
    }
}
