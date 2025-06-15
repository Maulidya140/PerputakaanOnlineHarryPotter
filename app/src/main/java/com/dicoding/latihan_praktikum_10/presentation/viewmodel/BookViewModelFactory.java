package com.dicoding.latihan_praktikum_10.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dicoding.latihan_praktikum_10.domain.repository.BookRepository;

public class BookViewModelFactory implements ViewModelProvider.Factory {
    private final BookRepository repository;

    public BookViewModelFactory(BookRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookViewModel.class)) {
            return (T) new BookViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
