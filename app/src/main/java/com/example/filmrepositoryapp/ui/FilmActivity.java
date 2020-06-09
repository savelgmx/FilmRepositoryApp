package com.example.filmrepositoryapp.ui;


import android.graphics.Color;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmrepositoryapp.FilmPresenter;
import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.model.Film;
import com.example.filmrepositoryapp.model.RealmManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.realm.Realm;
/*
        В этом задании работа с Realm. интерфейс FilmRepository
        стандартные методы получения, вставки, изменения, удаления элемента (Film).
                Плюс получения всех фильмов
        Добавить возможность генерации данных для заполнения БД
*/


public class FilmActivity extends AppCompatActivity  implements FilmPresenter.ViewContract {

    @BindView(R.id.main_root)
    ViewGroup root;

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    FilmPresenter filmPresenter;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Realm.init(getApplicationContext());
        RealmManager.initializeRealmConfig();
        super.onCreate(savedInstanceState);
        FilmScopeListener fragment = (FilmScopeListener) getSupportFragmentManager().findFragmentByTag("SCOPE_LISTENER");
        if(fragment == null) {
            fragment = new FilmScopeListener();
            getSupportFragmentManager().beginTransaction().add(fragment, "SCOPE_LISTENER").commit();
        }

        //get realm instance
        Realm realm = RealmManager.getRealm();

        //get presenter instance
        filmPresenter = fragment.getPresenter();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //set toolbar
/*
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
*/

        //setup recycler
        recycler.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        // get all persisted objects
        // changes will be reflected automatically
        recycler.setAdapter(new FilmAdapter(realm.where(Film.class).findAllAsync()));




    }


/*
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.actionAddRecord).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
*/

    @Override
    public void showAddFilmDialog() {

    }

    @Override
    public void showMissingFilmName() {

    }

    @Override
    public void showEditFilmDialog(Film film) {

    }
}
