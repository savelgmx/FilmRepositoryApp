package com.example.filmrepositoryapp.di;


import androidx.appcompat.app.AppCompatActivity;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.common.BaseViewModel;

public class SearchByDirectorActivityModule extends CommonActivityModule {


    public SearchByDirectorActivityModule(AppCompatActivity activity, String scopeName, int type) {
       super(activity, scopeName, type);
        //todo сделать интерфейсы

        bind(BaseViewModel.class).toProvider(SearchByDirectorViewModelProvider.class).providesSingletonInScope();
       // bind(SearchByDirectorViewModel.class).toProvider(SearchByDirectorViewModelProvider.class).providesSingletonInScope();
        bind(Integer.class).withName("TitleId").toInstance(R.string.director_search_title);
    }

}
