package com.example.filmrepositoryapp;

import android.app.Application;

import io.realm.Realm;
/*
        В этом задании работа с Realm. интерфейс FilmRepository
        стандартные методы получения, вставки, изменения, удаления элемента (Film).
                Плюс получения всех фильмов
        Добавить возможность генерации данных для заполнения БД
*/


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
