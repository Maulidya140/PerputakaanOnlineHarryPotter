package com.dicoding.latihan_praktikum_10.data.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenLibraryResponse {
    @SerializedName("docs")
    private List<BookDto> docs;

    public List<BookDto> getDocs() {
        return docs;
    }
}
