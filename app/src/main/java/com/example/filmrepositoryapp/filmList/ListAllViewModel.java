package com.example.filmrepositoryapp.filmList;

import com.example.alien.course04task02.data.IFilmRepository;
import com.example.alien.course04task02.data.model.Film;
import com.example.alien.course04task02.ui.common.BaseViewModel;
import com.google.gson.Gson;

import java.util.List;

public class ListAllViewModel extends BaseViewModel {

    public ListAllViewModel(IFilmRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        List<Film> films = mRepository.getAll();
        mFilmList.postValue(films);
    }
}
