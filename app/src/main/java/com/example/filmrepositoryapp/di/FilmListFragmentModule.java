package com.example.filmrepositoryapp.di;




import androidx.fragment.app.Fragment;

import com.example.filmrepositoryapp.ui.filmList.FilmListAdapter;
import com.example.filmrepositoryapp.ui.filmList.IOnItemClickListener;

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
