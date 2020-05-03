package com.example.filmrepositoryapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


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
        boolean shouldAddToBackStack = getSupportFragmentManager().findFragmentById(R.id.container) != null;

        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment);

        if (shouldAddToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        transaction.commit();
    }

}
