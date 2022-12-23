package kz.ermek.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Book {
    private int id;
    @NotEmpty(message = "Books name should not be empty")
    @Size(min = 2, max = 100, message = "Books name should be between 2 and 100 characters")
    private String name;
    @NotEmpty(message = "Authors name should not be empty")
    @Size(min = 2, max = 100, message = "Books name should be between 2 and 100 characters")
    private String author;
    @Min(value = 1800, message = "Year of publication should be greater then 1800")
    @Max(value = 2022, message = "Year of publication should be less then 2022")
    private int year_of_publication;

    public Book(String name, String author, int year_of_publication) {
        this.name = name;
        this.author = author;
        this.year_of_publication = year_of_publication;
    }

    public Book(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear_of_publication() {
        return year_of_publication;
    }

    public void setYear_of_publication(int year_of_publication) {
        this.year_of_publication = year_of_publication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
