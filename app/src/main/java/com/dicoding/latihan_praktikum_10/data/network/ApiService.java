package com.dicoding.latihan_praktikum_10.data.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("search.json?q=harry+potter")
    Call<OpenLibraryResponse> getBooks();
}

