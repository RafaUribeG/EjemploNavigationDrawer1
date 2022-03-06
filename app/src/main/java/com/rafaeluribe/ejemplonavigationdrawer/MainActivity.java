package com.rafaeluribe.ejemplonavigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout myDrawer;
    NavigationView myNav;
    Toolbar myToolbar;

    ActionBarDrawerToggle toogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDrawer = findViewById(R.id.myDrawerLayout);
        myNav = findViewById(R.id.myNavigationView);
        myToolbar = findViewById(R.id.myToolbar);

        setSupportActionBar(myToolbar);

        myNav.setNavigationItemSelectedListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.myFrame, new Fragmento1()).commit();
        setTitle("Home");

        //toogle = new ActionBarDrawerToggle(this, myDrawer, myToolbar, R.string.drawer_open, R.string.drawer_close);
        toogle = setDrawerToogle();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toogle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        toogle.syncState();
    }

    private ActionBarDrawerToggle setDrawerToogle(){
        return new ActionBarDrawerToggle(this, myDrawer, myToolbar,R.string.drawer_open, R.string.drawer_close);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (item.getItemId()){
            case R.id.nav_home:
                ft.replace(R.id.myFrame, new Fragmento1()).commit();
                return true;
            case R.id.nav_profile:
                ft.replace(R.id.myFrame, new Fragmento2()).commit();
                return true;
            case R.id.nav_event:
                ft.replace(R.id.myFrame, new Fragmento3()).commit();
                return true;
            case R.id.notification:
                Toast.makeText(MainActivity.this, "Elegiste Notificacion", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.nav_contact:
                Toast.makeText(MainActivity.this, "Elegiste Contacto", Toast.LENGTH_SHORT).show();
                return true;
        }
        setTitle(item.getTitle());
        myDrawer.closeDrawers();
        return false;
    }
}