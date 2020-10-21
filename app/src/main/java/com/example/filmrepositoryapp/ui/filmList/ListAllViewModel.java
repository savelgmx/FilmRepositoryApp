package com.example.filmrepositoryapp.ui.filmList;


import com.example.filmrepositoryapp.ui.common.BaseViewModel;
import com.example.filmrepositoryapp.model.FRepository;
import com.example.filmrepositoryapp.model.Film;
import com.google.gson.Gson;

import java.util.List;

public class ListAllViewModel extends BaseViewModel {

    public ListAllViewModel(FRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        List<Film> films = mRepository.getAll();
        mFilmList.postValue(films);
    }
}
