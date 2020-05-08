package com.example.filmrepositoryapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



import com.example.filmrepositoryapp.model.Film;
import com.example.filmrepositoryapp.model.FilmRepository;

import java.util.Date;
import java.util.List;

public class RealmTestActivity extends AppCompatActivity {

    private FilmRepository mFilmRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_realm_test);

        mFilmRepository = new FilmRepository();

        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Film item = mFilmRepository.getItem(getId());
                String text = item == null ? "null" : item.toString();
                Toast.makeText(RealmTestActivity.this, text, Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.getAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RealmTestActivity.this, show(mFilmRepository.getAll()), Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilmRepository.insertItem(getNewFilm());

            }
        });

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilmRepository.updateItem(getFilm());
            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilmRepository.deleteItem(getId());
            }
        });

    }

    private long getId() {
        return Long.parseLong(((EditText) findViewById(R.id.edit)).getText().toString());
    }

    private Film getFilm() {
        Film film = mFilmRepository.getItem(getId());
        return film;
    }

    private Film getNewFilm() {
        Film film = new Film();
        film.setRelease_date(new Date());
        film.setFilm_name("A some new film");
        film.setRating(10);
        return film;
    }

    private <T> String show(List<Film> list) {
        if (list == null || list.isEmpty()) return "empty";

        StringBuilder stringBuilder = new StringBuilder();

        for (Film t : list) {
            stringBuilder.append(t.toString()).append("\n");
        }

        return stringBuilder.toString();

    }
}

