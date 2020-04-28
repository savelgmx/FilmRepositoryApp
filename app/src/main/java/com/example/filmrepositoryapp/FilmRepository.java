package com.example.filmrepositoryapp;

import java.util.List;

public interface FilmRepository<Film> {
    Film getItem(long id);

    List<Film> getAll();

    long insertItem(Film film);

    boolean deleteItem(long id);

    void updateItem(Film film);

}
