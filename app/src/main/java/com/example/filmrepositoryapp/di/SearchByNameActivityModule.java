package com.example.filmrepositoryapp.di;



import androidx.appcompat.app.AppCompatActivity;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.common.BaseViewModel;


public class SearchByNameActivityModule extends CommonActivityModule {


    public SearchByNameActivityModule(AppCompatActivity activity, String scopeName, int type) {
        super(activity, scopeName, type);
        //todo сделать интерфейсы
        bind(BaseViewModel.class).toProvider(SearchByNameViewModelProvider.class).providesSingletonInScope();
       // bind(SearchByNameViewModel.class).toProvider(SearchByNameViewModelProvider.class).providesSingletonInScope();
        bind(Integer.class).withName("TitleId").toInstance(R.string.name_search_title);
    }

}
