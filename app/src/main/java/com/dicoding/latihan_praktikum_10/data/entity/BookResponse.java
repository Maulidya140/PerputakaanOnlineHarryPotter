package com.dicoding.latihan_praktikum_10.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponse {
    @SerializedName("docs")
    private List<BookEntity> docs;

    public List<BookEntity> getDocs() { return docs; }
    public void setDocs(List<BookEntity> docs) { this.docs = docs; }
}

