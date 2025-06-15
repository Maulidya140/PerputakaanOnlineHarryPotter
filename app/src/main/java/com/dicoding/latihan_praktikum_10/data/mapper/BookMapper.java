package com.dicoding.latihan_praktikum_10.data.mapper;

import com.dicoding.latihan_praktikum_10.data.entity.BookEntity;
import com.dicoding.latihan_praktikum_10.domain.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static List<Book> toDomainList(List<BookEntity> entities) {
        List<Book> books = new ArrayList<>();
        for (BookEntity entity : entities) {
            books.add(new Book(
                    entity.getTitle(),
                    entity.getAuthor(),
                    entity.getPublisher()
            ));
        }
        return books;
    }
}

