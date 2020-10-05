package com.example.filmrepositoryapp;

import android.app.Application;
import android.content.res.Configuration;
import android.widget.Toast;



import io.realm.Realm;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.registries.FactoryRegistryLocator;
import toothpick.registries.MemberInjectorRegistryLocator;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

/*
        Toothpick.setConfiguration(Configuration.forProduction().disableReflection());
        Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes());
*/
/*
        MemberInjectorRegistryLocator.setRootRegistry(new com.example.filmrepositoryapp.MemberInjectorRegistry());
        FactoryRegistryLocator.setRootRegistry(new com.example.filmrepositoryapp.FactoryRegistry());
*/
        Scope scope = Toothpick.openScope("Application");
        scope.installModules(new ApplicationModule(this));



        Toast.makeText(this, R.string.start_greeting, Toast.LENGTH_LONG).show();
    }
}
