package com.dicoding.latihan_praktikum_10.domain.repository;

import androidx.lifecycle.LiveData;

import com.dicoding.latihan_praktikum_10.domain.model.Book;

import java.util.List;

public interface BookRepository {
    void fetchBooksFromApi();
    LiveData<List<Book>> getAllBooks();
    LiveData<List<Book>> searchBooks(String query);
}

