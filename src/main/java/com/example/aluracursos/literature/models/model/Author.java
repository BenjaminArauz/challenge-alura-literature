package com.example.aluracursos.literature.models.model;

import com.example.aluracursos.literature.models.data.DataAuthor;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>();

    public Author() {}

    public Author(DataAuthor dataAuthor) {
        this.name = dataAuthor.name();
        this.birthYear = dataAuthor.birthYear() == null ? null : Integer.valueOf(dataAuthor.birthYear());
        this.deathYear = dataAuthor.deathYear() == null ? null : Integer.valueOf(dataAuthor.deathYear());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getFormattedName() {
        return name + " (" + birthYear + " - " + deathYear + ")";
    }

    @Override
    public String toString() {
        return "------------ AUTHOR ------------" + "\n" +
                "Name: " + name + "\n" +
                "Birth year: " + birthYear + "\n" +
                "Death year: " + deathYear + "\n" +
                "Books: " + getBooksString() + "\n";
    }

    private String getBooksString() {
        return books.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(", "));
    }
}
