package com.example.filmrepositoryapp.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class Film extends RealmObject {
    @PrimaryKey
    private long id;
    private String film_name;//название фильма
    @Index
    private String directors_name; //имя режиссера
    private int release_date;//дата выхода фильма т.к.это обычно год без месяцев и дат довольно целого числа
    private int rating;//рейтинг фильма
    private String description;//описание фильма
    //теперь записываенм методы геттеры/сеттеры для заполнения полей
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setDirectors_name(String directors_name) {
        this.directors_name = directors_name;
    }

    public String getDirectors_name() {
        return directors_name;
    }

    public void setRelease_date(int release_date ) { this.release_date = release_date; }
    public int getRelease_date() { return  release_date; }

    public void  setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setDescription(String s) { this.description=s; }
    public String getDescription(){return description;}
}
