package com.example.filmrepositoryapp.model;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.Sort;

public class FilmRepository implements FRepository {

    private static final String TAG = "FilmRepository";
    private static final int MIN_LENGTH_FOR_NAME_SEARCH = 3;
    private static final int MIN_LENGTH_FOR_DIRECTOR_SEARCH = 4;
    private AtomicLong currentId = new AtomicLong();
    private Realm mRealm;

    private static RealmConfiguration realmConfiguration;

    public FilmRepository() {


        mRealm = Realm.getDefaultInstance();
        Number number = mRealm.where(Film.class).max("id");
        if (number != null) {
            currentId.set(number.longValue());
        } else {
            currentId.set(0);
        }

    }
    @Override
    public long insertItem(Film film) {
        film.setId(currentId.incrementAndGet());
        mRealm.beginTransaction();
        mRealm.copyToRealm(film);
        mRealm.commitTransaction();
        EventBus.getDefault().post(new OnFilmDataBaseUpdate());
        return film.getId();
    }

    @Override
    public void insertItems(List<Film> films) {
        for (Film film : films) {
            insertItem(film);
        }
    }

    @Override
    public Film getItem(long id) {
        return mRealm.copyFromRealm(getFilmById(id));
    }

    @Override
    public boolean deleteItem(long id) {
        boolean isSuccessful = false;
        Film film = getFilmById(id);
        mRealm.beginTransaction();
        if (film != null) {
            film.deleteFromRealm();
            isSuccessful = true;
        }
        mRealm.commitTransaction();
        EventBus.getDefault().post(new OnFilmDataBaseUpdate());
        return isSuccessful;
    }

    @Override
    public List<Film> getAll() {
        return mRealm.copyFromRealm(mRealm.where(Film.class)
                .findAll()
                .sort("id", Sort.ASCENDING));
    }

    @Override
    public void updateItem(Film Film) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(Film);
        mRealm.commitTransaction();
        EventBus.getDefault().post(new OnFilmDataBaseUpdate());
    }

    private Film getFilmById(long id) {
        return mRealm.where(Film.class).equalTo("id", id).findFirst();
    }

    public long createFilmAndSave(String film_name, String director, int year, double rating,String image_url) {
        //public Film(Long id, String film_name, String directors_name,int release_date, double rating,String image_url)
        Film film = new Film(0L, film_name,  director,year, rating,image_url);
        return insertItem(film);
    }
/*
    Добавьте возможность поиска по диапазону года выхода фильма, метод List<Film> searchInBounds(int startYear, int endYear);
    Добавьте возможность поиска фильмов определенного режиссера, методList<Film> searchByDirector(String name).
    Имя может быть указано не полностью, минимум 4 символа, только в начале слова.
    Добавьте возможность получить топ фильмов по рейтингу (double поле)List<Film> getTopFilms(int count)
*/
@Override
public List<Film> search(String query) {
    if (query != null && query.length() >= MIN_LENGTH_FOR_NAME_SEARCH) {
        query = "*" + query + "*";
        return mRealm.copyFromRealm(mRealm.where(Film.class)
                .like("film_name", query, Case.INSENSITIVE)
                .findAll());
    } else {
        return new ArrayList<>();
    }
}

    @Override
    public List<Film> searchInBounds(int startYear, int endYear) {
        if (endYear == 0) {
            return mRealm.copyFromRealm(mRealm.where(Film.class)
                    .equalTo("release_date", startYear)
                    .sort("release_date", Sort.ASCENDING)
                    .findAll());
        } else {
            return mRealm.copyFromRealm(mRealm.where(Film.class)
                    .between("release_date", startYear, endYear)
                    .sort("release_date", Sort.ASCENDING)
                    .findAll());
        }
    }

    @Override
    public List<Film> searchByDirector(String directors_name) {
        if (directors_name == null || directors_name.length() < MIN_LENGTH_FOR_DIRECTOR_SEARCH) {
            return new ArrayList<>();
        }
        return mRealm.copyFromRealm(mRealm.where(Film.class)
                .beginsWith("directors_name", directors_name, Case.INSENSITIVE)
                .findAll());
    }

    @Override
    public List<Film> getTopFilms(int count) {

        List<Film> retList = new ArrayList<>();

        List<Film> results = mRealm.copyFromRealm(mRealm.where(Film.class).sort("rating", Sort.DESCENDING).findAll());
        for (Film film : results) {
            if (retList.size() >= count) {
                break;
            }
            retList.add(film);
        }
        return retList;
    }


    @Override
    public void createFilmAndUpdate(long id, String film_name, String directors_name, int year, double rating,String image_url) {
        //public Film(Long id, String film_name, String directors_name,int release_date, double rating,String image_url)

        Film film = new Film(id, film_name,directors_name, year, rating,image_url);
        updateItem(film);
    }
    class OnFilmDataBaseUpdate implements IOnFilmDataBaseUpdate {
    }


}
