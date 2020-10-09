package com.example.filmrepositoryapp.model;


import java.util.List;

public interface FRepository {
    Film getItem(long id);

    List<Film> getAll();

    long insertItem(Film film);
    void insertItems(List<Film> films);

    boolean deleteItem(long id);

    void updateItem(Film film);

    List<Film> search(String query);

    List<Film> searchInBounds(int startYear, int endYear);
    List<Film> searchByDirectorsName(String directors_name);

    List<Film> getTopFilms(int count); //выбрать Т фильмов с наивысщим рейтингом

    long createFilmAndSave(String name, String director, int year, double rating);
    void createFilmAndUpdate(long id, String film_name, String directors_name, int year, double rating,String image_url);

    interface IOnFilmDataBaseUpdate {}
}
