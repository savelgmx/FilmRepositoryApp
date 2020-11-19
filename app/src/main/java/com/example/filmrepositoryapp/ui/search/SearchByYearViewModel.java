package com.example.filmrepositoryapp.ui.search;


import android.text.TextUtils;

import androidx.core.util.Pair;
import androidx.lifecycle.MutableLiveData;

import com.example.filmrepositoryapp.ui.common.BaseViewModel;
import com.example.filmrepositoryapp.model.FRepository;
import com.google.gson.Gson;

import java.util.regex.Pattern;

import timber.log.Timber;


public class SearchByYearViewModel extends BaseViewModel {

    private MutableLiveData<String> mSearchByYearQuery = new MutableLiveData<>();

    public SearchByYearViewModel(FRepository repository, Gson gson) {
        super(repository, gson);
        updateFromRepository();
    }

    public MutableLiveData<String> getSearchByYearQuery() {
        return mSearchByYearQuery;
    }

    public void setSearchByYearQuery(CharSequence query) {
        this.mSearchByYearQuery.setValue(query.toString());
        updateFromRepository();
    }

    private Pair<Integer, Integer> parseYearQuery() {
        int startYear = 0;
        int endYear = 0;
        if (mSearchByYearQuery.getValue() != null) {
            String splitResult[] = TextUtils.split(mSearchByYearQuery.getValue(), Pattern.compile("\\D+"));
            try {
                startYear = Integer.valueOf(splitResult[0]);
            } catch (Throwable t) {
                Timber.d(t);
                startYear = 0;
            }

            if (splitResult.length > 1) {
                try {
                    endYear = Integer.valueOf(splitResult[1]);
                } catch (Throwable t) {
                    Timber.d(t);
                    endYear = 0;
                }
            }
        }
        if (endYear < startYear) {
            endYear = 0;
        }
        Timber.d("startYear: %d", startYear);
        Timber.d("endYear: %d", endYear);
        return new Pair<>(startYear, endYear);
    }

    @Override
    protected void updateFromRepository() {
        Pair<Integer, Integer> parseResult = parseYearQuery();
        mFilmList.postValue(mRepository.searchInBounds(parseResult.first, parseResult.second));
    }
}
