package com.example.filmrepositoryapp.ui;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.model.Film;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmModel;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;


public class FilmAdapter extends RealmRecyclerViewAdapter<Film,FilmsHolder> {

    public static OnItemClickListener onItemClickListener;
    @NonNull
        private final List<Film> mFilms = new ArrayList<>();
       // private final OnItemClickListener mOnClickListener;

        public FilmAdapter(RealmResults<Film> films) {
            super(films,true);
           // mOnClickListener = onClickListener;
        }
/*
    public FilmAdapter(RealmResults<Film> allAsync) {
        super(allAsync,true);
    }
*/

    @Override
        public FilmsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_item_film, parent, false);
            return new FilmsHolder(view);
        }



    @Override
        public void onBindViewHolder(FilmsHolder holder, final int position) {

            final Film film = getItem(position);
            if (film != null){
              //  holder.bind(film,mOnClickListener);
                holder.bind(film);

            }

        }


        @Override
        public int getItemCount() {
            return mFilms.size();
        }

        public void addData(List<Film> data, boolean isRefreshed) {
            if (isRefreshed) {
                mFilms.clear();
            }

            mFilms.addAll(data);
            notifyDataSetChanged();
        }



    public interface OnItemClickListener {
            void onItemClick(Film film);
        }
    }


