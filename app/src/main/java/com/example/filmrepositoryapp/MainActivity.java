package com.example.filmrepositoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.realm.Realm;

/*
        В этом задании работа с Realm. интерфейс FilmRepository
        стандартные методы получения, вставки, изменения, удаления элемента (Film).
                Плюс получения всех фильмов
        Добавить возможность генерации данных для заполнения БД
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Realm.init(this); // Initialize Realm


    }
}
