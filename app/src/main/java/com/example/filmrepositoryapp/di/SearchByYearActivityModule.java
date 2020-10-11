package com.example.filmrepositoryapp.di;


import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task02.R;
import com.example.alien.course04task02.ui.common.BaseViewModel;


public class SearchByYearActivityModule extends CommonActivityModule {


    public SearchByYearActivityModule(AppCompatActivity activity, String scopeName, int type) {
       super(activity, scopeName, type);
        //todo сделать интерфейсы

        bind(BaseViewModel.class).toProvider(SearchByYearViewModelProvider.class).providesSingletonInScope();
        //bind(SearchByYearViewModel.class).toProvider(SearchByYearViewModelProvider.class).providesSingletonInScope();
        bind(Integer.class).withName("TitleId").toInstance(R.string.year_search_title);
    }

}
