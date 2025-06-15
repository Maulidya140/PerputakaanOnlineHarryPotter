package com.dicoding.latihan_praktikum_10.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dicoding.latihan_praktikum_10.data.entity.BookEntity;

import java.util.List;

@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBooks(List<BookEntity> books);

    @Query("SELECT * FROM books")
    LiveData<List<BookEntity>> getBooks();

    @Query("SELECT * FROM books WHERE title LIKE :keyword")
    LiveData<List<BookEntity>> searchBooks(String keyword);

    @Query("SELECT * FROM books")
    List<BookEntity> getBooksSync();
}
