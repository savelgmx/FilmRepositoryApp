package com.example.filmrepositoryapp.ui.search;


import androidx.lifecycle.MutableLiveData;

import com.example.filmrepositoryapp.common.BaseViewModel;
import com.example.filmrepositoryapp.model.FRepository;
import com.google.gson.Gson;


public class SearchByTopViewModel extends BaseViewModel {

    private MutableLiveData<String> mSearchByTopQuery = new MutableLiveData<>();

    public SearchByTopViewModel(FRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }


    public MutableLiveData<String> getSearchByTopQuery() {
        return mSearchByTopQuery;
    }

    public void setSearchByTopQuery(CharSequence query) {
        this.mSearchByTopQuery.setValue(query.toString());
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        int count = 0;
        try {
            count = Integer.valueOf(mSearchByTopQuery.getValue());
        } catch (Throwable t) {
           // Timber.d(t);
        }
        mFilmList.postValue(mRepository.getTopFilms(count));
    }
}
