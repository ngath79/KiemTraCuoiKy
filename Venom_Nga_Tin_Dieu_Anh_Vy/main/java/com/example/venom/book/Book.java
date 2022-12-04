package com.example.venom.book;

public class Book {
    private int IdBook;
    private String nameBook;
    private String actor;
    private String year;
    private int img;


    public Book(int idBook, String nameBook, String actor, String year, int img) {
        IdBook = idBook;
        this.nameBook = nameBook;
        this.actor = actor;
        this.year = year;
        this.img = img;
    }

    public int getIdBook() {
        return IdBook;
    }

    public void setIdBook(int idBook) {
        IdBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
