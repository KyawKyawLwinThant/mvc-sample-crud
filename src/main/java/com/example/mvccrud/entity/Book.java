package com.example.mvccrud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearPublished;
    private String publisher;
    @ManyToOne
    private Author author;
}
