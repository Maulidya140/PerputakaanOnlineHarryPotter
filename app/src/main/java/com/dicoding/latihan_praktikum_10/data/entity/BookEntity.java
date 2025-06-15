package com.dicoding.latihan_praktikum_10.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class BookEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String author;
    private String publisher;

    public BookEntity(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    // Getter dan Setter
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
}
