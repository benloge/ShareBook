package fr.sharebookstore.app.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.utils.Navigation;

public class NewAccountActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        Navigation.SetTopToolbar(NewAccountActivity.this, this);
        Navigation.setBottomNavigation(NewAccountActivity.this,this,R.id.action_accueil);
    }

    @Override
    public void onClick(View v) {

    }
}