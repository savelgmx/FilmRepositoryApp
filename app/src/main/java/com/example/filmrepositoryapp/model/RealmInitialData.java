package com.example.filmrepositoryapp.model;

import io.realm.Realm;
//Класс дает возможность генерации данных для заполнения БД.
public class RealmInitialData implements Realm.Transaction {
    @Override
    public void execute(Realm realm) {
        Film film = new Film();

        film.setId(1);
        film.setDirectors_name("Robert Zemekis");
        film.setFilm_name("Forrest Gump");
        film.setRelease_date(1994);
        film.setRating(10);
        realm.insertOrUpdate(film);

        film.setId(2);
        film.setDirectors_name("Robert Zemekis");
        film.setFilm_name("Back to the Furure");
        film.setRelease_date(1985);
        film.setRating(10);
        realm.insertOrUpdate(film);

        film.setId(3);
        film.setDirectors_name("Robert Zemekis");
        film.setFilm_name("Back to the Future II");
        film.setRelease_date(1989);
        film.setRating(10);
        realm.insertOrUpdate(film);

        film.setId(4);
        film.setDirectors_name("Robert Zemekis");
        film.setFilm_name("Back to the Future III");
        film.setRelease_date(1990);
        film.setRating(10);
        realm.insertOrUpdate(film);

        film.setId(5);
        film.setDirectors_name("Sergio Leone");
        film.setFilm_name("Once upon a time in America");
        film.setRelease_date(1984);
        film.setRating(50);
        realm.insertOrUpdate(film);

        film.setId(6);
        film.setDirectors_name("Sergio Leone");
        film.setFilm_name("A fistful of dollars");
        film.setRelease_date(1964);
        film.setRating(20);
        realm.insertOrUpdate(film);

        film.setId(7);
        film.setDirectors_name("Sergio Leone");
        film.setFilm_name("For a few dollars more");
        film.setRelease_date(1965);
        film.setRating(20);
        realm.insertOrUpdate(film);

        film.setId(8);
        film.setDirectors_name("Sergio Leone");
        film.setFilm_name("The Good, the Bad and the Ugly");
        film.setRelease_date(1966);
        film.setRating(20);
        realm.insertOrUpdate(film);

    }

    @Override
    public int hashCode() {
        return RealmInitialData.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof RealmInitialData;
    }
}



