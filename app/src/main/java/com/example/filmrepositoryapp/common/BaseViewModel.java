package com.example.filmrepositoryapp.common;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.filmrepositoryapp.model.FRepository;
import com.example.filmrepositoryapp.model.Film;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmResults;

public abstract class BaseViewModel extends ViewModel {
    protected MutableLiveData<List<Film>> mFilmList = new MutableLiveData<>();
    protected OrderedRealmCollection<Film> data;
    private MutableLiveData<Boolean> mIsEmpty = new MutableLiveData<>();

    protected FRepository mRepository;

    private Gson mGson;

    public BaseViewModel(FRepository repository, Gson gson) {
        this.mRepository = repository;
        this.mGson = gson;

        EventBus.getDefault().register(this);

        mFilmList.observeForever(list ->
        {
            mIsEmpty.postValue(list != null && list.isEmpty());

            if (list instanceof RealmResults) {
                RealmResults<Film> filmRealmResults = (RealmResults<Film>) list;
                filmRealmResults.addChangeListener(films -> mIsEmpty.postValue(films.isEmpty()));
            }
        });
    }

    public MutableLiveData<List<Film>> getFilmList() {
        return mFilmList;
    }

    public MutableLiveData<Boolean> getIsEmpty() {
        return mIsEmpty;
    }

    public void generateData(String json) {
        Type type = new TypeToken<List<Film>>() {
        }.getType();
        List<Film> films = mGson.fromJson(json, type);
        mRepository.insertItems(films);
    }

    public void deleteItem(long id) {
        mRepository.deleteItem(id);
    }

    public OrderedRealmCollection<Film> getData() {
        return data;
    }

    @Override
    protected void onCleared() {
        EventBus.getDefault().unregister(this);
        super.onCleared();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFilmDataBaseUpdate(FRepository.IOnFilmDataBaseUpdate event)
    {
        updateFromRepository();
    }

    abstract protected void updateFromRepository();
}
