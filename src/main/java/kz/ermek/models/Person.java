package kz.ermek.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "FIO should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String fio;

    @Min(value = 1900, message = "Date of birth should be greater than 1900")
    @Max(value = 2006, message = "Date of birth should be less than 2006")
    private int year;

    public Person(){

    }

    public Person(String fio, int year) {
        this.fio = fio;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
