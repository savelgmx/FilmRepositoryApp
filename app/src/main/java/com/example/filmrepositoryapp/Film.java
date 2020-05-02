package com.example.filmrepositoryapp;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Film extends RealmObject {
    @PrimaryKey
    private long id;
    private String film_name;//название фильма
    private String directors_name; //имя режиссера
    private Date release_date;//дата выхода фильма
    private int rating;//рейтинг фильма
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

    public void setRelease_date(Date release_date ) { this.release_date = release_date; }
    public Date getRelease_date() { return  release_date; }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}
