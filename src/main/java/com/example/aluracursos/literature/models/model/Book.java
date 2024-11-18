package com.example.aluracursos.literature.models.model;

import com.example.aluracursos.literature.models.data.DataBook;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String title;
    private List<String> subjects;
    private Integer downloads;
    private List<String> languages;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    public Book() {}

    public Book(DataBook dataBook) {
        this.title = dataBook.title();
        this.authors = dataBook.authors()
                .stream()
                .map(author -> new Author(author))
                .collect(Collectors.toSet());

        this.subjects = dataBook.subjects();
        this.downloads = dataBook.downloads();
        this.languages = dataBook.languages();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "------------ BOOK ------------" + "\n" +
                "Title:" + title + "\n" +
                "Authors: " + getAuthorsString() + "\n" +
                "Subjects: " + subjects + "\n" +
                "Downloads: " + downloads + "\n" +
                "Languages: " + languages + "\n";
    }

    private String getAuthorsString() {
        return authors.stream()
                .map(Author::getFormattedName)
                .collect(Collectors.joining(", "));
    }
}
