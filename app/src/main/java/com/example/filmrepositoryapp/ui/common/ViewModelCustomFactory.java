package com.example.filmrepositoryapp.ui.common;



import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.example.filmrepositoryapp.model.FRepository;
import com.example.filmrepositoryapp.ui.filmList.ListAllViewModel;
import com.example.filmrepositoryapp.ui.search.SearchByDirectorViewModel;
import com.example.filmrepositoryapp.ui.search.SearchByNameViewModel;
import com.example.filmrepositoryapp.ui.search.SearchByTopViewModel;
import com.example.filmrepositoryapp.ui.search.SearchByYearViewModel;
import com.google.gson.Gson;

public class ViewModelCustomFactory implements ViewModelProvider.Factory {
    private FRepository mRepository;
    private Gson mGson;

    public ViewModelCustomFactory(FRepository repository, Gson gson) {

        mRepository = repository;
        mGson = gson;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == SearchByNameViewModel.class) {
            return (T) new SearchByNameViewModel(mRepository, mGson);
        }
        if(modelClass == SearchByDirectorViewModel.class) {
            return (T) new SearchByDirectorViewModel(mRepository, mGson);
        }
        if(modelClass == SearchByYearViewModel.class) {
            return (T) new SearchByYearViewModel(mRepository, mGson);
        }
        if(modelClass == SearchByTopViewModel.class) {
            return (T) new SearchByTopViewModel(mRepository, mGson);
        }
        return (T) new ListAllViewModel(mRepository, mGson);
    }
}
