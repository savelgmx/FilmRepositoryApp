package com.example.filmrepositoryapp.ui;


import android.content.DialogInterface;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmrepositoryapp.FilmPresenter;
import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.model.Film;
import com.example.filmrepositoryapp.model.FilmRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

import butterknife.OnClick;
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
         FilmRepository.initializeRealmConfig();


        super.onCreate(savedInstanceState);
        FilmScopeListener fragment = (FilmScopeListener) getSupportFragmentManager().findFragmentByTag("SCOPE_LISTENER");
        if(fragment == null) {
            fragment = new FilmScopeListener();
            getSupportFragmentManager().beginTransaction().add(fragment, "SCOPE_LISTENER").commit();
        }

        //get realm instance
          Realm realm = FilmRepository.getRealm();

        //get presenter instance
        filmPresenter = fragment.getPresenter();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //set toolbar
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        //setup recycler
        recycler.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        // get all persisted objects
        // changes will be reflected automatically
        recycler.setAdapter(new FilmAdapter(realm.where(Film.class).findAllAsync()));
        if(savedInstanceState == null) {
            Toast.makeText(this, "ДЛИННОЕ НАЖАТИЕ УДАЛЯЕТ ФИЛЬМ ИЗ СПИСКА!" +
                    "КОРОТКОЕ-редактирует!", Toast.LENGTH_LONG).show();
        }

        // bind to presenter
        filmPresenter.bindView(this);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.searchByName).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    @Override
    protected void onDestroy() {
        if(filmPresenter != null) {
            filmPresenter.unbindView();
        }
        if(dialog != null) {
            dialog.dismiss();
        }
        super.onDestroy();
    }

    @OnClick(R.id.fab)
    void onFabClicked() {
        filmPresenter.showAddDialog();
    }


    @Override
    public void showAddFilmDialog() {

        final View content = getLayoutInflater().inflate(R.layout.edit_item, root, false);
        final DialogContract dialogContract = (DialogContract) content;

        AlertDialog.Builder builder = new AlertDialog.Builder(FilmActivity.this);
        builder.setView(content)
                .setTitle(getString(R.string.add_film))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        filmPresenter.saveFilm(dialogContract);
                        filmPresenter.dismissAddDialog();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        filmPresenter.dismissAddDialog();
                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();

    }

    @Override
    public void showMissingFilmName() {
        Toast.makeText(FilmActivity.this,getString(R.string.entry_not_saved),
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showEditFilmDialog(Film film) {

        if(!film.isValid()) {
            return;
        }
        final View content = getLayoutInflater().inflate(R.layout.edit_item, root, false);
        final FilmPresenter.ViewContract.DialogContract dialogContract = (FilmPresenter.ViewContract.DialogContract) content;
        dialogContract.bind(film);

        final long id = film.getId();

        AlertDialog.Builder builder = new AlertDialog.Builder(FilmActivity.this);
        builder.setView(content)
                .setTitle(getString(R.string.edit_film))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        filmPresenter.editFilm(dialogContract, id);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    @Override
    public Object getSystemService(String name) {
        if(name.equals(FilmPresenter.TAG)) {
            return filmPresenter;
        }
        return super.getSystemService(name);
    }

}
