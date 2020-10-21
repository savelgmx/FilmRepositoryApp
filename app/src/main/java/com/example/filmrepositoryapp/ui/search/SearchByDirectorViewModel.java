package com.example.filmrepositoryapp.ui.search;


import androidx.lifecycle.MutableLiveData;

import com.example.filmrepositoryapp.ui.common.BaseViewModel;
import com.example.filmrepositoryapp.model.FRepository;
import com.google.gson.Gson;

public class SearchByDirectorViewModel extends BaseViewModel {

    private MutableLiveData<String> mSearchByDirectorQuery = new MutableLiveData<>();

    public SearchByDirectorViewModel(FRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }


    public MutableLiveData<String> getSearchByDirectorQuery() {
        return mSearchByDirectorQuery;
    }

    public void setSearchByDirectorQuery(CharSequence query) {
        this.mSearchByDirectorQuery.setValue(query.toString());
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        mFilmList.postValue(mRepository.searchByDirector(mSearchByDirectorQuery.getValue()));
    }
}
