package com.softwaretechit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelSearch {
 @SerializedName("nextPageToken")
 @Expose
 private String nextPageToken;

 @SerializedName("items")
 @Expose
private List<SearchYT> items;


 public ModelSearch() {
 }

 public ModelSearch(String nextPageToken, List<SearchYT> items) {
  this.nextPageToken = nextPageToken;
  this.items = items;
 }

 public String getNextPageToken() {
  return nextPageToken;
 }

 public void setNextPageToken(String nextPageToken) {
  this.nextPageToken = nextPageToken;
 }

 public List<SearchYT> getItems() {
  return items;
 }

 public void setItems(List<SearchYT> items) {
  this.items = items;
 }
}
