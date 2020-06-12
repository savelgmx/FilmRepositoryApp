package com.example.filmrepositoryapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.model.Film;
import java.util.ArrayList;
import java.util.List;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;


public class FilmAdapter extends RealmRecyclerViewAdapter<Film, FilmsViewHolder> {

    @NonNull
    private final List<Film> mFilms = new ArrayList<>();

    public FilmAdapter(RealmResults<Film> films) {
        super(films,true);
    }

    // create new views (invoked by the layout manager)
    @Override
    public FilmsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_films, parent, false);
        return new FilmsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmsViewHolder holder, final int position) {

        final Film film = getItem(position);
        if (film != null){
            holder.bind(film);

        }

    }



}






