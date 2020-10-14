package com.example.filmrepositoryapp.model;

import io.realm.Realm;
//Класс дает возможность генерации данных для заполнения БД.
public class RealmInitialData implements Realm.Transaction {
    @Override
    public void execute(Realm realm) {
        Film film = new Film();

        film.setId(1);
        film.setDirectors_name("Robert Zemekis");
        film.setFilmName("Forrest Gump");
        film.setRelease_date(1994);
        film.setRating(10);
        film.setImageUrl("https://m.media-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg");
        realm.insertOrUpdate(film);

        film.setId(2);
        film.setDirectors_name("Robert Zemekis");
        film.setFilmName("Back to the Future");
        film.setRelease_date(1985);
        film.setRating(10);
        film.setImageUrl("https://m.media-amazon.com/images/M/MV5BZmU0M2Y1OGUtZjIxNi00ZjBkLTg1MjgtOWIyNThiZWIwYjRiXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX182_CR0,0,182,268_AL_.jpg");
        realm.insertOrUpdate(film);

        film.setId(3);
        film.setDirectors_name("Robert Zemekis");
        film.setFilmName("Back to the Future II");
        film.setRelease_date(1989);
        film.setRating(10);
        film.setImageUrl("https://m.media-amazon.com/images/M/MV5BZTMxMGM5MjItNDJhNy00MWI2LWJlZWMtOWFhMjI5ZTQwMWM3XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX182_CR0,0,182,268_AL_.jpg");
        realm.insertOrUpdate(film);

        film.setId(4);
        film.setDirectors_name("Robert Zemekis");
        film.setFilmName("Back to the Future III");
        film.setRelease_date(1990);
        film.setRating(10);
        film.setImageUrl("https://m.media-amazon.com/images/M/MV5BYjhlMGYxNmMtOWFmMi00Y2M2LWE5NWYtZTdlMDRlMGEzMDA3XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX182_CR0,0,182,268_AL_.jpg");
        realm.insertOrUpdate(film);

        film.setId(5);
        film.setDirectors_name("Sergio Leone");
        film.setFilmName("Once upon a time in America");
        film.setRelease_date(1984);
        film.setRating(50);
        film.setImageUrl("https://m.media-amazon.com/images/M/MV5BMGFkNWI4MTMtNGQ0OC00MWVmLTk3MTktOGYxN2Y2YWVkZWE2XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg");
        realm.insertOrUpdate(film);

        film.setId(6);
        film.setDirectors_name("Sergio Leone");
        film.setFilmName("A fistful of dollars");
        film.setRelease_date(1964);
        film.setRating(20);
        film.setImageUrl("https://m.media-amazon.com/images/M/MV5BYjA1MGVlMGItNzgxMC00OWY4LWI4YjEtNTNmYWIzMGUxOGQzXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_UY268_CR1,0,182,268_AL_.jpg");
        realm.insertOrUpdate(film);

        film.setId(7);
        film.setDirectors_name("Sergio Leone");
        film.setFilmName("For a few dollars more");
        film.setRelease_date(1965);
        film.setRating(20);
        film.setImageUrl("https://m.media-amazon.com/images/M/MV5BNWM1NmYyM2ItMTFhNy00NDU0LThlYWUtYjQyYTJmOTY0ZmM0XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg");
        realm.insertOrUpdate(film);

        film.setId(8);
        film.setDirectors_name("Sergio Leone");
        film.setFilmName("The Good, the Bad and the Ugly");
        film.setRelease_date(1966);
        film.setRating(20);
        film.setImageUrl("https://m.media-amazon.com/images/M/MV5BOTQ5NDI3MTI4MF5BMl5BanBnXkFtZTgwNDQ4ODE5MDE@._V1_UX182_CR0,0,182,268_AL_.jpg");
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



