package com.example.filmrepositoryapp.model;

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
    private double rating;//рейтинг фильма
    private String image_url;//ссылка на картинку постер фильма

    public Film(){};

    public Film(Long id, String film_name, String directors_name,int release_date, double rating,String image_url) {
        this.id = id;
        this.film_name = film_name;
        this.directors_name = directors_name;
        this.release_date = release_date;
        this.rating = rating;
        this.image_url=image_url;
    }

    //теперь записываенм методы геттеры/сеттеры для заполнения полей
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setFilmName(String film_name) {
        this.film_name = film_name;
    }
    public String getFilmName() {
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

    public void  setRating(double rating) {
        this.rating = rating;
    }
    public double getRating() {
        return rating;
    }


    public String getImageUrl() {
        return image_url;
    }
    public void setImageUrl(String imageUrl) {
        this.image_url = imageUrl;
    }


}
