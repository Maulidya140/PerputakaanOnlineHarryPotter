package com.dicoding.latihan_praktikum_10.domain.model;

public class Book {
    private final String title;
    private final String author;
    private final String publisher;

    public Book(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
}
