package com.example.filmrepositoryapp.model;

import android.util.Log;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class FilmRepository implements FRepository  {

    private static final String TAG="FilmRepository";

    private static Realm realm;
    private static AtomicLong currentId;
    private static RealmConfiguration realmConfiguration;

    public FilmRepository(){


        realm = Realm.getDefaultInstance();
        Number max = realm.where(Film.class).max("id");
        currentId = max ==null? new AtomicLong(0):new AtomicLong(max.longValue());

          if (max != null) {
            currentId.set(max.longValue());
        } else {
            currentId.set(0);
    }


    private Film getRealmAssosiatedFilm(long id) {
        return realm.where(Film.class).equalTo("id",id).findFirst();
    }

    @Override
    public Film getItem(long id) {
        Film film = getRealmAssosiatedFilm(id);
        return film != null? realm.copyFromRealm(film) :null;

    }

    @Override
    public List<Film> getAll() {
        return realm.where(Film.class).findAll();
    }


    public static RealmResults<Film> getAllAsync() {
        return realm.where(Film.class).findAllAsync();
    }

    private Film getRealmAssociatedFilm(long id) {
        return realm.where(Film.class).equalTo("id", id).findFirst();
    }

    @Override
    public long insertItem(Film film) {
        film.setId(sPrimaryId.incrementAndGet());
        realm.beginTransaction();
        realm.copyToRealm(film);
        realm.commitTransaction();
        return sPrimaryId.longValue();
    }


    @Override
    public boolean deleteItem(long id) {
        boolean isDeleteSuccessful;
        realm.beginTransaction();
        Film film = getRealmAssociatedFilm(id);

        if (film != null) {
            film.deleteFromRealm();
            isDeleteSuccessful = true;
        } else {
            isDeleteSuccessful = false;
        }
        realm.commitTransaction();
        return isDeleteSuccessful;
    }


    @Override
    public void updateItem(Film film) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(film);
        realm.commitTransaction();
    }

/*
    Добавьте возможность поиска по диапазону года выхода фильма, метод List<Film> searchInBounds(int startYear, int endYear);
    Добавьте возможность поиска фильмов определенного режиссера, методList<Film> searchByDirector(String name).
    Имя может быть указано не полностью, минимум 4 символа, только в начале слова.
    Добавьте возможность получить топ фильмов по рейтингу (double поле)List<Film> getTopFilms(int count)
*/
    public List<Film> searchInBounds(int startYear,int endYear){
        return realm.where(Film.class).between("release_date",startYear,endYear).findAll();
    }

    public List<Film> searchByDirector(String directorsName){
        return realm.where(Film.class).contains("directors_name",directorsName).findAll();
    }

    public List<Film> getTopFilms(int count){
        return realm.where(Film.class).equalTo("rating",count).findAll();
    }
  /*
  здесь добавляем иниуиализцию реалм для заполнения начальнвми данными
  и для подсчета числа экземляров дааного объекта
   * */
  public static void initializeRealmConfig() {

      //сюда переносим код из конструктрора
      realm = Realm.getDefaultInstance();
      Number max = realm.where(Film.class).max("id");
      sPrimaryId = max ==null? new AtomicLong(0):new AtomicLong(max.longValue());



      if(realmConfiguration == null) {
          Log.d(TAG, "Film Repository Initializing Realm configuration.");
          setRealmConfiguration(new RealmConfiguration.Builder() //
                  .initialData(new RealmInitialData())
                  .deleteRealmIfMigrationNeeded()
                  .build());
      }
  }

    public static void setRealmConfiguration(RealmConfiguration realmConfiguration) {
        FilmRepository.realmConfiguration = realmConfiguration;
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private static int activityCount = 0;

    public static Realm getRealm() {
        return realm;
    }

    public static void incrementCount() {
        if(activityCount == 0) {
            if(realm != null) {
                if(!realm.isClosed()) {
                    Log.w(TAG, "Unexpected open Realm found.");
                    realm.close();
                }
            }
            Log.d(TAG, "Incrementing Activity Count [0]: opening Realm.");
            realm = Realm.getDefaultInstance();
        }
        activityCount++;
        Log.d(TAG, "Increment: Count [" + activityCount + "]");
    }

    public static void decrementCount() {
        activityCount--;
        Log.d(TAG, "Decrement: Count [" + activityCount + "]");
        if(activityCount <= 0) {
            Log.d(TAG, "Decrementing Activity Count: closing Realm.");
            activityCount = 0;
            realm.close();
            if(Realm.compactRealm(realmConfiguration)) {
                Log.d(TAG, "Realm compacted successfully.");
            }
            realm = null;
        }
    }

}
