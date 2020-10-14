package com.example.filmrepositoryapp;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.filmrepositoryapp.model.Film;
import com.example.filmrepositoryapp.model.FilmRepository;


import io.realm.Realm;

public class FilmPresenter {
    @SuppressLint("WrongConstant")
    public static FilmPresenter getService(Context context) {
        //noinspection ResourceType
        return (FilmPresenter) context.getSystemService(TAG);
    }

    public static final String TAG = "FilmPresenter";

    public interface ViewContract {
        void showAddFilmDialog();

        void showMissingFilmName();

        void showEditFilmDialog(Film film);

        interface DialogContract {
            String getFilmName();
            String getDirectorsName();
            int getRating();
            int getReleaseDate();

            void bind(Film film);
        }
    }

    ViewContract viewContract;

    boolean isDialogShowing;

    boolean hasView() {
        return viewContract != null;
    }

    public void bindView(ViewContract viewContract) {
        this.viewContract = viewContract;
        if(isDialogShowing) {
            showAddDialog();
        }
    }

    public void unbindView() {
        this.viewContract = null;
    }

    public void showAddDialog() {
        if(hasView()) {
            isDialogShowing = true;
            viewContract.showAddFilmDialog();
        }
    }

    public void dismissAddDialog() {
        isDialogShowing = false;
    }

    public void showEditDialog(Film film) {
        if(hasView()) {
            viewContract.showEditFilmDialog(film);
        }
    }

    public void saveFilm(ViewContract.DialogContract dialogContract) {
        if(hasView()) {
            final String directorsName = dialogContract.getDirectorsName();
            final String filmName = dialogContract.getFilmName();
            final int rating = dialogContract.getRating();

            if(filmName == null || "".equals(filmName.trim())) {
                viewContract.showMissingFilmName();
            } else {
                Realm realm = FilmRepository.getRealm();
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Film film = new Film();
                        long id = 1;
                        if(realm.where(Film.class).count() > 0) {
                            id = realm.where(Film.class).max("id").longValue() + 1; // auto-increment id
                        }
                        film.setId(id);
                        film.setDirectors_name(directorsName);
                         film.setRating(rating);
                        film.setFilmName(filmName);
                        realm.insertOrUpdate(film);
                    }
                });
            }
        }
    }

    public void deleteFilmById(final long id) {
        Realm realm = FilmRepository.getRealm();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Film film = realm.where(Film.class).equalTo("id",id).findFirst();
                if(film != null) {
                    film.deleteFromRealm();
                }
            }
        });
    }

    public void editFilm(final ViewContract.DialogContract dialogContract, final long id) {
        Realm realm = FilmRepository.getRealm();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
               Film film = realm.where(Film.class).equalTo("id", id).findFirst();
                if(film != null) {
                    film.setFilmName(dialogContract.getFilmName());
                    film.setRating(dialogContract.getRating());
                    film.setDirectors_name(dialogContract.getDirectorsName());
                    film.setRelease_date(dialogContract.getReleaseDate());
                }
            }
        });
    }

}
