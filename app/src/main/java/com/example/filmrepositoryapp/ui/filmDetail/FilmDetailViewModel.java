package com.example.filmrepositoryapp.ui.filmDetail;


import androidx.lifecycle.MutableLiveData;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.ui.common.BaseViewModel;
import com.example.filmrepositoryapp.model.FRepository;
import com.example.filmrepositoryapp.model.Film;
import com.google.gson.Gson;

import timber.log.Timber;

public class FilmDetailViewModel extends BaseViewModel {
    private MutableLiveData<String> mName = new MutableLiveData<>();
    private MutableLiveData<String> mYear = new MutableLiveData<>();
    private MutableLiveData<String> mDirector = new MutableLiveData<>();
    private MutableLiveData<String> mRating = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsSaved = new MutableLiveData<>();
    private MutableLiveData<String> mImageURL = new MutableLiveData<>();
    private Long mFilmId;
    private int mTitleId;

    public FilmDetailViewModel(FRepository repository, Gson gson, Long filmId) {
        super(repository, gson);
        mIsSaved.postValue(false);
        mFilmId = filmId;
        if (mFilmId >= 0) {
            loadFilm();
            mTitleId = R.string.dialog_title_edit_film;
        } else {
            mTitleId = R.string.dialog_title_new_film;
        }
    }

    private void loadFilm() {
        Film film = mRepository.getItem(mFilmId);
        mName.postValue(film.getFilmName());
        mDirector.postValue(film.getDirectors_name());
        mYear.postValue(String.valueOf(film.getRelease_date()));
        mRating.postValue(String.valueOf(film.getRating()));
        mImageURL.postValue(String.valueOf(film.getImageUrl()));
    }

    public MutableLiveData<String> getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName.postValue(mName);
    }

    public MutableLiveData<String> getYear() {
        return mYear;
    }


    public MutableLiveData<String> getDirector() {
        return mDirector;
    }

    public MutableLiveData<String> getRating() {
        return mRating;
    }

    public MutableLiveData<String> getImageURL(){return mImageURL;}


    public void apply(String name, String director, String year, String rating,String image_url) {
        int yearInt = 0;
        double ratingDbl = 0;
        try {
            yearInt = Integer.valueOf(year);
            ratingDbl = Double.valueOf(rating);
        } catch (Throwable t) {
            Timber.d(t);
        }
        if (mFilmId < 0) {
            //    long createFilmAndSave(String film_name, String directors_name, int year, double rating,String image_url);
            mRepository.createFilmAndSave(name, director, yearInt, ratingDbl,image_url);
        } else {
            mRepository.createFilmAndUpdate(mFilmId, name, director, yearInt, ratingDbl,image_url);
        }
        mIsSaved.postValue(true);
    }

    public MutableLiveData<Boolean> getIsSaved() {
        return mIsSaved;
    }

    public int getTitleId() {
        return mTitleId;
    }

    @Override
    protected void updateFromRepository() {

    }
}
