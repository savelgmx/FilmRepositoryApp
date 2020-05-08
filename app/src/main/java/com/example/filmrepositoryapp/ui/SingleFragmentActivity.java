package com.example.filmrepositoryapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import com.example.filmrepositoryapp.R;


/*
Абстактный класс базовый для всех трех фрагментов данного приложения
На его основе может быть произвольноее число фрагментов
Инициализирует контейнер для фрагментов в методе OnCreate
Заканчивает работу приложения если Back button pressed и если нет другой активити/фрагмента
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_fragment);
        if (savedInstanceState == null) {
            changeFragment(getFragment());
        }
    }

    protected abstract Fragment getFragment();

    protected void changeFragment(@NonNull Fragment fragment) {
        boolean shouldAddToBackStack = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer) != null;

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment);

        if (shouldAddToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        transaction.commit();
    }

}
