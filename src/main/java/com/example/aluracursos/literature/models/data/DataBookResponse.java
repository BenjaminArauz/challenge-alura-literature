package com.example.aluracursos.literature.models.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBookResponse(
        @JsonAlias("results") List<DataBook> books
) {
}
