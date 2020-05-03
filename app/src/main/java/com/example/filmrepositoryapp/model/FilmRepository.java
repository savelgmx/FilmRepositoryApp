package com.example.filmrepositoryapp.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;

public class FilmRepository implements FRepository<Film> {

    private Realm mRealm;
    private static AtomicLong sPrimaryId;

    public FilmRepository(){
        mRealm = Realm.getDefaultInstance();
        Number max = mRealm.where(Film.class).max("id");
        sPrimaryId = max ==null? new AtomicLong(0):new AtomicLong(max.longValue());

    }


    private Film getRealmAssosiatedFilm(long id) {
        return mRealm.where(Film.class).equalTo("id",id).findFirst();
    }

    @Override
    public Film getItem(long id) {
        Film film = getRealmAssosiatedFilm(id);
        return film != null?mRealm.copyFromRealm(film) :null;

    }

    @Override
    public List<Film> getAll() {
        return mRealm.where(Film.class).findAll();
    }

    private Film getRealmAssociatedFilm(long id) {
        return mRealm.where(Film.class).equalTo("id", id).findFirst();
    }

    @Override
    public long insertItem(Film film) {
        film.setId(sPrimaryId.incrementAndGet());
        mRealm.beginTransaction();
        mRealm.copyToRealm(film);
        mRealm.commitTransaction();
        return sPrimaryId.longValue();
    }


    @Override
    public boolean deleteItem(long id) {
        boolean isDeleteSuccessful;
        mRealm.beginTransaction();
        Film film = getRealmAssociatedFilm(id);

        if (film != null) {
            film.deleteFromRealm();
            isDeleteSuccessful = true;
        } else {
            isDeleteSuccessful = false;
        }
        mRealm.commitTransaction();
        return isDeleteSuccessful;
    }


    @Override
    public void updateItem(Film film) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(film);
        mRealm.commitTransaction();
    }

/*
    Добавьте возможность поиска по диапазону года выхода фильма, метод List<Film> searchInBounds(int startYear, int endYear);
    Добавьте возможность поиска фильмов определенного режиссера, методList<Film> searchByDirector(String name). Имя может быть указано не полностью, минимум 4 символа, только в начале слова.
    Добавьте возможность получить топ фильмов по рейтингу (double поле)List<Film> getTopFilms(int count)
*/
    public List<Film> searchInBounds(int startYear,int endYear){
        return mRealm.where(Film.class).between("relaese_date",startYear,endYear).findAll();
    }

}
