package fr.sharebookstore.app.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.utils.Navigation;

public class NouveauteActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveaute);
        Navigation.SetTopToolbar(NouveauteActivity.this, this);
        Navigation.setBottomNavigation(NouveauteActivity.this,this,R.id.action_store);

    }

    @Override
    public void onClick(View v) {
        Navigation.OnclickTopToolbar(v,this);
    }
}