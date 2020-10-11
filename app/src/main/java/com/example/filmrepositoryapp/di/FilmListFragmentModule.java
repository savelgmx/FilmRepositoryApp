package com.example.filmrepositoryapp.di;


import android.support.v4.app.Fragment;

import com.example.alien.course04task02.ui.filmList.FilmListAdapter;
import com.example.alien.course04task02.ui.filmList.IOnItemClickListener;

import toothpick.config.Module;

public class FilmListFragmentModule extends Module {
    private Fragment mFragment;

    public FilmListFragmentModule(Fragment fragment) {
        mFragment = fragment;

        if (mFragment instanceof IOnItemClickListener) {
            bind(FilmListAdapter.class).toInstance(new FilmListAdapter((IOnItemClickListener) mFragment));
        } else {
            bind(FilmListAdapter.class).toInstance(new FilmListAdapter(null));
        }
    }


}
