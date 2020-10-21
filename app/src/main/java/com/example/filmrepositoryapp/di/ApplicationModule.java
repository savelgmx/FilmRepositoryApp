package com.example.filmrepositoryapp.di;

import android.app.Application;


import com.example.filmrepositoryapp.ui.common.ViewModelCustomFactory;
import com.example.filmrepositoryapp.model.FRepository;
import com.example.filmrepositoryapp.model.FilmRepository;
import com.google.gson.Gson;

import toothpick.config.Module;

public class ApplicationModule extends Module {

    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;

        bind(FRepository.class).toInstance(new FilmRepository());
        bind(Gson.class).toInstance(new Gson());
        bind(ViewModelCustomFactory.class).toProvider(ViewModelCustomFactoryProvider.class).providesSingletonInScope();
        bind(Application.class).toInstance(mApplication);
    }
}
