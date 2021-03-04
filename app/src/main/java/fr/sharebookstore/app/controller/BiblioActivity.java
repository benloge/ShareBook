package fr.sharebookstore.app.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.utils.Navigation;
import fr.sharebookstore.app.utils.NetworkRecyclerViewDocument;

public class BiblioActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mPreferences;

    public static final String PREF_KEY_ID = "PREF_KEY_ID";
    public static final String PREF_KEY_PSEUDO = "PREF_KEY_PSEUDO";
    public static final String PREF_KEY_NOM = "PREF_KEY_NOM";
    public static final String PREF_KEY_PRENOM = "PREF_KEY_PRENOM";
    public static final String PREF_KEY_EMAIL = "PREF_KEY_EMAIL";
    public static final String PREF_KEY_TEL = "PREF_KEY_TEL";
    public static final String PREF_KEY_GENRE = "PREF_KEY_GENRE";
    public static final String PREF_KEY_PASSWORD = "PREF_KEY_PASSWORD";
    public static final String PREF_KEY_STATUS = "PREF_KEY_STATUS";

    private boolean reloadNeeded = true;

    private static final int EDIT_CODE = 31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblio);

        mPreferences = getSharedPreferences("user", MODE_PRIVATE);
        Navigation.SetTopToolbar(BiblioActivity.this, this);
        Navigation.setBottomNavigation(BiblioActivity.this,this,R.id.action_biblio);
        getImages();
    }

    private void getImages() {

        String pseudo = mPreferences.getString(PREF_KEY_PSEUDO, null);
        String password = mPreferences.getString(PREF_KEY_PASSWORD, null);
        if (pseudo != null && password != null) {
            String requeteMaBibliotheque = "http://18.159.181.250/api/utilisateur.php?action=getuserdocument&pseudo=" + pseudo + "&mdp=" + password;
            new NetworkRecyclerViewDocument(this, BiblioActivity.this, R.id.Biblio_MesAchat, "ListVertical").execute(requeteMaBibliotheque);
        } else {
            startActivityForResult(new Intent(BiblioActivity.this,LoginActivity.class), EDIT_CODE);
            this.onDestroy();
        }


    }

    @Override
    public void onClick(View v) {
        Navigation.OnclickTopToolbar(v, this);
    }


}