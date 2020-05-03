package com.example.filmrepositoryapp;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.filmrepositoryapp.model.Film;
import com.example.filmrepositoryapp.model.FilmRepository;

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
                mFilmRepository.insertItem(getNewTrack());

            }
        });

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFilmRepository.updateItem(getTrack());
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

    private Track getTrack() {
        Track track = mFilmRepository.getItem(getId());
        track.setDuration(66666);
        return track;
    }

    private Track getNewTrack() {
        Track track = new Track();
        track.setDate(new Date());
        track.setDistance(123.4);
        track.setDuration(13123);
        return track;
    }

    private <T> String show(List<T> list) {
        if (list == null || list.isEmpty()) return "empty";

        StringBuilder stringBuilder = new StringBuilder();

        for (T t : list) {
            stringBuilder.append(t.toString()).append("\n");
        }

        return stringBuilder.toString();

    }
}

