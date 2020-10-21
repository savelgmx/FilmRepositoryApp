package com.example.filmrepositoryapp.ui.search;

import androidx.lifecycle.MutableLiveData;

import com.example.filmrepositoryapp.ui.common.BaseViewModel;
import com.example.filmrepositoryapp.model.FRepository;
import com.google.gson.Gson;

public class SearchByNameViewModel extends BaseViewModel {

    private MutableLiveData<String> mSearchByNameQuery = new MutableLiveData<>();

    public SearchByNameViewModel(FRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }

    public MutableLiveData<String> getSearchByNameQuery() {
        return mSearchByNameQuery;
    }

    public void setSearchByNameQuery(CharSequence query) {
        this.mSearchByNameQuery.setValue(query.toString());
        updateFromRepository();
    }

    @Override
    protected void updateFromRepository() {
        mFilmList.postValue(mRepository.search(mSearchByNameQuery.getValue()));
    }
}
