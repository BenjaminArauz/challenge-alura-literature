package com.example.aluracursos.literature.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> clase);
}
