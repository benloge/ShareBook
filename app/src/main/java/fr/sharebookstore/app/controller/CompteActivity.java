package fr.sharebookstore.app.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.utils.Navigation;

public class CompteActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> arrayList=new ArrayList<>();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);

        mPreferences = getSharedPreferences("user",MODE_PRIVATE);

        setViewList();
        setBottomNavigation();
        Navigation.SetTopToolbar(CompteActivity.this, this);
    }

    private void setBottomNavigation() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_accueil);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected() {
                return onNavigationItemSelected();
            }

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String classname = this.getClass().getName();
                switch (item.getItemId()) {
                    case R.id.action_accueil:
                        if (!classname.contains("MainActivity")) {
                            startActivity(new Intent(CompteActivity.this, MainActivity.class));
                        }

                        break;
                    case R.id.action_store:
                        if (!classname.contains("StoreActivity")) {
                            // startActivity(new Intent(CompteActivity.this, StoreActivity.class));
                        }

                        break;
                    case R.id.action_biblio:
                        if (!classname.contains("BiblioActivity")) {
                            startActivity(new Intent(CompteActivity.this, BiblioActivity.class));
                        }
                        break;
                    case R.id.action_panier:
                        if (!classname.contains("PanierActivity")) {
                            startActivity(new Intent(CompteActivity.this, PanierActivity.class));
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void setViewList() {
        ListView listView=(ListView)findViewById(R.id.list_view_compte);

        boolean status = mPreferences.getBoolean(PREF_KEY_STATUS, Boolean.FALSE);

        if (status == Boolean.TRUE) {
            arrayList.add("Modifier Compte");
            arrayList.add("Gestion Abonnement");
            arrayList.add("Déconnexion");
        }
        else {
            arrayList.add("S'authentifier");
            arrayList.add("Créer un compte");
        }
        arrayList.add("Mention Légal");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (arrayList.get(i).toString()) {
                    case "S'authentifier":
                        startActivity(new Intent(CompteActivity.this, LoginActivity.class));
                        break;
                    case "Déconnexion":
                        mPreferences.edit().clear().commit();
                        startActivity(new Intent(CompteActivity.this, MainActivity.class));
                        break;
                    default:
                        Toast.makeText(CompteActivity.this,"clicked item:"+i+" "+arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
    }
}