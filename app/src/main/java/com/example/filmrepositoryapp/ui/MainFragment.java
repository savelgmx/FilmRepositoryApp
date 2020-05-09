package com.example.filmrepositoryapp.ui;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefresher;
    private View mErrorView;


    @Override
    public void onRefresh() {
        mRefresher.post(this::getFilms);

    }

    private void getFilms() {
        //достаем из realm
    }
}
