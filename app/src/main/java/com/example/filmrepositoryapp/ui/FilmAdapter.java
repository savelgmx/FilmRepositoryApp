package com.example.filmrepositoryapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.model.Film;

import java.util.ArrayList;
import java.util.List;



public class FilmAdapter extends RecyclerView.Adapter<FilmsHolder> {


        @NonNull
        private final List<Film> mFilms = new ArrayList<>();
        private final OnItemClickListener mOnClickListener;

        public FilmAdapter(OnItemClickListener onClickListener) {
            mOnClickListener = onClickListener;
        }

        @Override
        public FilmsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_item_film, parent, false);
            return new FilmsHolder(view);
        }

        @Override
        public void onBindViewHolder(FilmsHolder holder, int position) {
            Film film = mFilms.get(position);
            holder.bind(film, mOnClickListener);
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


