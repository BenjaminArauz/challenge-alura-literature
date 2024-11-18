package com.example.aluracursos.literature.models.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<DataAuthor> authors,
        @JsonAlias("subjects") List<String> subjects,
        @JsonAlias("download_count") Integer downloads,
        @JsonAlias("languages") List<String> languages
){
}
