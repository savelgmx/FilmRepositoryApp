package com.example.filmrepositoryapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.model.Film;
import com.example.filmrepositoryapp.model.FilmRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.RealmQuery;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

/*
https://www.google.com/search?newwindow=1&sxsrf=ALeKk00A0lQjtpGQs94mCsj30g6LLZlmUw:1593585620678&q=realmrecyclerviewadapter+implements+filterable&sa=X&ved=2ahUKEwjpp9uGuavqAhVJw8QBHWlAC-4Q7xYoAHoECAsQKg&biw=1309&bih=717

https://www.programcreek.com/java-api-examples/?code=Zhuinden%2Frealm-helpers%2Frealm-helpers-master%2Frealm-auto-migration-example%2Fsrc%2Fmain%2Fjava%2Fcom%2Fzhuinden%2Frealmautomigrationexample%2FMainActivity.java#
  */

public class FilmAdapter extends RealmRecyclerViewAdapter<Film, FilmsViewHolder> implements Filterable {

    @NonNull
    private final List<Film> mFilms = new ArrayList<>();

    public FilmAdapter(RealmResults<Film> films) {
        super(films, true);
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
        if (film != null) {
            holder.bind(film);

        }

    }

    public void filterResults(String text) {
        text = text == null ? null : text.toLowerCase().trim();
        RealmQuery<Film> query = FilmRepository.getRealm().where(Film.class);
        if (!(text == null || "".equals(text))) {
            query.contains("film_name", text, Case.INSENSITIVE);// TODO: change field
        }
        updateData(query.findAllAsync());
    }


    @Override
    public Filter getFilter() {
        return new FilmFilter(this);
    }

    private class FilmFilter extends Filter {
        private final FilmAdapter adapter;

        private FilmFilter(FilmAdapter adapter) {
            super();
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return new FilterResults();
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.filterResults(constraint.toString());
        }
    }
}



