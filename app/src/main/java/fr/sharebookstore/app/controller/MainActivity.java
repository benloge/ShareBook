package fr.sharebookstore.app.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomNavigation();

        getImages();
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
                            startActivity(new Intent(MainActivity.this, MainActivity.class));
                        }

                        break;
                    case R.id.action_store:
                        if (!classname.contains("StoreActivity")) {
                            startActivity(new Intent(MainActivity.this, StoreActivity.class));
                        }

                        break;
                    case R.id.action_biblio:
                        if (!classname.contains("BiblioActivity")) {
                            startActivity(new Intent(MainActivity.this, BiblioActivity.class));
                        }
                        break;
                    case R.id.action_panier:
                        if (!classname.contains("PanierActivity")) {
                            startActivity(new Intent(MainActivity.this, PanierActivity.class));
                        }
                        break;
                }
                return true;
            }
        });
    }

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<String> mImageUrls2 = new ArrayList<>();

    private void getImages(){
        Log.d(TAG, "IniImageBitmaps : preparing bitmaps.");

        mImageUrls.add("http://18.159.181.250/image/bel-ami_Guy_de_Maupassant.jpg");
        mNames.add("Bel-Ami");
        mImageUrls.add("http://18.159.181.250/image/bel-ami_Guy_de_Maupassant.jpg");
        mNames.add("Bel-Ami");
        mImageUrls.add("http://18.159.181.250/image/bel-ami_Guy_de_Maupassant.jpg");
        mNames.add("Bel-Ami");
        mImageUrls.add("http://18.159.181.250/image/bel-ami_Guy_de_Maupassant.jpg");
        mNames.add("Bel-Ami");
        mImageUrls.add("http://18.159.181.250/image/bel-ami_Guy_de_Maupassant.jpg");
        mNames.add("Bel-Ami");
        mImageUrls.add("http://18.159.181.250/image/bel-ami_Guy_de_Maupassant.jpg");
        mNames.add("Bel-Ami");
        mImageUrls.add("http://18.159.181.250/image/bel-ami_Guy_de_Maupassant.jpg");
        mNames.add("Bel-Ami");

        mImageUrls2.add("http://18.159.181.250/image/les_avantures_de_Sherlock_Holmes_Arthur_Conan_Doyle.jpg");
        mNames2.add("Les Aventures de Sherlock Holmes");
        mImageUrls2.add("http://18.159.181.250/image/les_avantures_de_Sherlock_Holmes_Arthur_Conan_Doyle.jpg");
        mNames2.add("Les Aventures de Sherlock Holmes");
        mImageUrls2.add("http://18.159.181.250/image/les_avantures_de_Sherlock_Holmes_Arthur_Conan_Doyle.jpg");
        mNames2.add("Les Aventures de Sherlock Holmes");
        mImageUrls2.add("http://18.159.181.250/image/les_avantures_de_Sherlock_Holmes_Arthur_Conan_Doyle.jpg");
        mNames2.add("Les Aventures de Sherlock Holmes");
        mImageUrls2.add("http://18.159.181.250/image/les_avantures_de_Sherlock_Holmes_Arthur_Conan_Doyle.jpg");
        mNames2.add("Les Aventures de Sherlock Holmes");
        mImageUrls2.add("http://18.159.181.250/image/les_avantures_de_Sherlock_Holmes_Arthur_Conan_Doyle.jpg");
        mNames2.add("Les Aventures de Sherlock Holmes");

        initRecyclerView(R.id.nouveauteView, mNames, mImageUrls);
        initRecyclerView(R.id.tendanceView, mNames2, mImageUrls2);


    }
    private void initRecyclerView(int i, ArrayList<String> name, ArrayList<String> image ){
        Log.d(TAG,  "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager =  new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL,  false);
        RecyclerView recyclerView = findViewById(i);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( this, name, image);
        recyclerView.setAdapter(adapter);
    }


}