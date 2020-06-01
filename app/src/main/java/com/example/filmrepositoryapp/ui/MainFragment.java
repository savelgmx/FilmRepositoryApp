package com.example.filmrepositoryapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.RealmTestActivity;
import com.example.filmrepositoryapp.model.Film;
import com.example.filmrepositoryapp.model.FilmRepository;
import com.example.filmrepositoryapp.model.RealmManager;

import io.realm.Realm;

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefresher;
    private View mErrorView;
    private FilmRepository mFilmRepository;

    public static MainFragment newInstance() {
        return new MainFragment();
    }
    @NonNull
/*
    private final FilmAdapter mFilmsAdapter = new FilmAdapter(film -> getFragmentManager().beginTransaction()
            .replace(R.id.fragmentContainer, DetailFilmFragment.newInstance(film))
            .addToBackStack(DetailFilmFragment.class.getSimpleName())
            .commit());
*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recycler);
        mRefresher = view.findViewById(R.id.refresher);
        mRefresher.setOnRefreshListener(this);
        mErrorView = view.findViewById(R.id.errorView);

        mFilmRepository = new FilmRepository();
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle(R.string.films);

        //get realm instance
    //    Realm realm = RealmManager.getRealm();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter((RecyclerView.Adapter) FilmRepository.getAllAsync());

       // mRecyclerView.setAdapter(new FilmAdapter(realm.where(Film.class).findAllAsync()));

//new BooksAdapter(realm.where(Book.class).findAllAsync())
        onRefresh();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.actionAddRecord).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionAddRecord:
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, AddRecordFragment.newInstance())
                        .addToBackStack(MainFragment.class.getSimpleName())
                        .commit();

                return true;
            case R.id.actionEditRecord:
                //EditRecordActivity.start(this);


                return true;
            case R.id.actionExit:
                getActivity().finish(); //убиваем текущую Активити
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onRefresh() {
        mRefresher.post(this::getFilms);

    }

    private void getFilms() {
        //достаем из realm список
        mFilmRepository.getAll();

    }


}
