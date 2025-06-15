package com.dicoding.latihan_praktikum_10.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.dicoding.latihan_praktikum_10.domain.model.Book;
import com.dicoding.latihan_praktikum_10.domain.repository.BookRepository;
import java.util.List;

public class BookViewModel extends ViewModel {
    private final BookRepository repository;

    public BookViewModel(BookRepository repository) {
        this.repository = repository;

        // 🔥 Tambahkan baris ini supaya ambil data dari API saat ViewModel dibuat
        repository.fetchBooksFromApi();
    }

    public LiveData<List<Book>> getBooks() {
        return repository.getAllBooks();
    }

    public LiveData<List<Book>> searchBooks(String keyword) {
        return repository.searchBooks("%" + keyword + "%");
    }
}