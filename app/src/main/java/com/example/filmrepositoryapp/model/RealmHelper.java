package com.example.filmrepositoryapp.model;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //WRITE
    public void save(final Film film)
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                //Film film=realm.copyToRealm(film);

            }
        });

    }

    //READ
    public ArrayList<String> retrieve()
    {
        ArrayList<String> filmNames=new ArrayList<>();
        RealmResults<Film> films=realm.where(Film.class).findAll();

        for(Film film:films)
        {
            filmNames.add(film.getFilm_name());
        }

        return filmNames;
    }
}

