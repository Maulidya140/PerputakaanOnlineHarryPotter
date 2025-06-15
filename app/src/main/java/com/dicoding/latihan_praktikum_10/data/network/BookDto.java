package com.dicoding.latihan_praktikum_10.data.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookDto {
    @SerializedName("title")
    private String title;

    @SerializedName("author_name")
    private List<String> authorName;

    @SerializedName("publisher")
    private List<String> publisher;

    public String getTitle() {
        return title;
    }

    public List<String> getAuthorName() {
        return authorName;
    }

    public List<String> getPublisher() {
        return publisher;
    }
}
