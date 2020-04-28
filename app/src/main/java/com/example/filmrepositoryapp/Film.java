package com.example.filmrepositoryapp;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Film extends RealmObject {
    @PrimaryKey
    private int id;
    private String film_name;//название фильма
    private String directors_name; //имя режиссера
    private Date release_date;//дата выхода фильма
    private int rating;//рейтинг фильма


}
