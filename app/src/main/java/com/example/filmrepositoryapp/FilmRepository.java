package com.example.filmrepositoryapp;

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

    @Override
    public long insertItem(Film film) {
        return 0;
    }


    @Override
    public boolean deleteItem(long id) {
        return false;
    }

    @Override
    public void updateItem(Film film) {

    }

}
//TODO наполнение БД