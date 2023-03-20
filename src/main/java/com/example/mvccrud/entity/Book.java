package com.example.mvccrud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Title cannot be empty!")
    private String title;
    @Min(value = 10,message = "Price is too low!")
    @Max(value = 100,message = "Price is too high!")
    private double price;
    @Past(message = "Year Published must be past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearPublished;
    @NotEmpty(message = "Publisher cannnot be empty!")
    private String publisher;
    @NotEmpty(message = "Image cannot be empty!")
    private String imgUrl;
    @ManyToOne
    private Author author;
    @Transient
    private boolean render;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
