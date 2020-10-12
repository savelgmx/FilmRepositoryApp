package com.example.filmrepositoryapp.di;


import com.example.filmrepositoryapp.common.ViewModelCustomFactory;
import com.example.filmrepositoryapp.model.FRepository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelCustomFactoryProvider implements Provider<ViewModelCustomFactory> {

    protected FRepository mRepository;
    private Gson mGson;

    @Inject
    public ViewModelCustomFactoryProvider(FRepository mRepository, Gson gson) {
        this.mRepository = mRepository;
        this.mGson = gson;
    }

    @Override
    public ViewModelCustomFactory get() {
        return new ViewModelCustomFactory(mRepository, mGson);
    }
}
