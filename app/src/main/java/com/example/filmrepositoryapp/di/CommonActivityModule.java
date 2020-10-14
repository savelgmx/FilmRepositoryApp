package com.example.filmrepositoryapp.di;


import androidx.appcompat.app.AppCompatActivity;

import com.example.filmrepositoryapp.ui.main.MainFragment;
import com.example.filmrepositoryapp.ui.filmList.ListAllFragment;

import toothpick.config.Module;

public class CommonActivityModule extends Module {

    private AppCompatActivity mActivity;
    private String mScopeName;
    private int mType;

    public CommonActivityModule(AppCompatActivity activity, String scopeName, int type) {
        mActivity = activity;
        mScopeName = scopeName;
        mType = type;

        bind(MainFragment.class).toInstance(MainFragment.newInstance(mScopeName, mType));
        bind(ListAllFragment.class).toInstance(ListAllFragment.newInstance(mScopeName));
        bind(AppCompatActivity.class).toInstance(mActivity);
    }
}