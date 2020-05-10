package com.example.filmrepositoryapp.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.model.FilmRepository;

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

//TODO getAll() from realmDB

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefresher;
    private View mErrorView;
    private FilmAdapter mFilmsAdapter;
    private FilmRepository mFilmRepository;

    public static MainFragment newInstance() {
        return new MainFragment();
    }
    @NonNull
 /*   private final FilmAdapter mFilmsAdapter = new FilmAdapter(film -> getFragmentManager().beginTransaction()
            .replace(R.id.fragmentContainer, DetailFilmFragment.newInstance(film))
            .addToBackStack(DetailFilmFragment.class.getSimpleName())
            .commit());
*/
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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(mFilmsAdapter);

        onRefresh();
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
