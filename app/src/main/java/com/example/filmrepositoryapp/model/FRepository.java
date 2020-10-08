package com.example.filmrepositoryapp.model;

import java.util.List;

public interface FRepository {
    Film getItem(long id);

    List<Film> getAll();

    long insertItem(Film film);

    boolean deleteItem(long id);

    void updateItem(Film film);

}
