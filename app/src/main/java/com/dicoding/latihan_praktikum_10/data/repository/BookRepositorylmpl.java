package com.dicoding.latihan_praktikum_10.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.dicoding.latihan_praktikum_10.data.database.BookDao;
import com.dicoding.latihan_praktikum_10.data.entity.BookEntity;
import com.dicoding.latihan_praktikum_10.data.mapper.BookMapper;
import com.dicoding.latihan_praktikum_10.data.network.ApiService;
import com.dicoding.latihan_praktikum_10.data.network.BookDto;
import com.dicoding.latihan_praktikum_10.data.network.OpenLibraryResponse;
import com.dicoding.latihan_praktikum_10.domain.model.Book;
import com.dicoding.latihan_praktikum_10.domain.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepositorylmpl implements BookRepository {

    private final ApiService apiService;
    private final BookDao bookDao;
    private final ExecutorService executorService;

    public BookRepositorylmpl(ApiService apiService, BookDao bookDao) {
        this.apiService = apiService;
        this.bookDao = bookDao;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void fetchBooksFromApi() {
        apiService.getBooks().enqueue(new Callback<OpenLibraryResponse>() {
            @Override
            public void onResponse(Call<OpenLibraryResponse> call, Response<OpenLibraryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<BookDto> docs = response.body().getDocs();
                    List<BookEntity> entities = new ArrayList<>();
                    for (BookDto dto : docs) {
                        String title = dto.getTitle() != null ? dto.getTitle() : "No Title";
                        String author = dto.getAuthorName() != null && !dto.getAuthorName().isEmpty()
                                ? dto.getAuthorName().get(0) : "Unknown Author";
                        String publisher = dto.getPublisher() != null && !dto.getPublisher().isEmpty()
                                ? dto.getPublisher().get(0) : "Unknown Publisher";


                        entities.add(new BookEntity(title, author, publisher));
                    }

                    executorService.execute(() -> bookDao.insertBooks(entities));
                } else {
                    Log.e("BookRepositoryImpl", "API response unsuccessful or empty");
                }
            }

            @Override
            public void onFailure(Call<OpenLibraryResponse> call, Throwable t) {
                Log.e("BookRepositoryImpl", "API call failed", t);
            }
        });
    }

    @Override
    public LiveData<List<Book>> getAllBooks() {
        return Transformations.map(bookDao.getBooks(), BookMapper::toDomainList);
    }

    @Override
    public LiveData<List<Book>> searchBooks(String query) {
        return Transformations.map(bookDao.searchBooks("%" + query + "%"), BookMapper::toDomainList);
    }
}

