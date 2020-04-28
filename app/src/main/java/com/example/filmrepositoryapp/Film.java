package com.example.filmrepositoryapp;

import java.util.List;

public class Film implements FilmRepository {
    @Override
    public Object getItem(long id) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public long insertItem(Object o) {
        return 0;
    }

    @Override
    public boolean deleteItem(long id) {
        return false;
    }

    @Override
    public void updateItem(Object o) {

    }
}
//TODO поля названия,года выхода фильма.режиссера,топ фильмов по рейтингу
//TODO наполнение БД