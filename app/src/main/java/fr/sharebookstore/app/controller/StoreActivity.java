package fr.sharebookstore.app.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import fr.sharebookstore.app.R;
import fr.sharebookstore.app.utils.NetworkRecyclerViewCategorie;
import fr.sharebookstore.app.utils.NetworkRecyclerViewDocument;
import fr.sharebookstore.app.utils.RecyclerViewAdapter;
import fr.sharebookstore.app.utils.Navigation;

public class StoreActivity extends AppCompatActivity implements View.OnClickListener {

    TextView nouveaute_clickable;
    TextView tendance_clickable;
    TextView auteur_clickable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        Navigation.SetTopToolbar(StoreActivity.this, this);
        Navigation.setBottomNavigation(StoreActivity.this,this,R.id.action_store);
        getImages();

        nouveaute_clickable=(TextView)findViewById(R.id.store_nouveaute);
        nouveaute_clickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StoreActivity.this,NouveauteActivity.class);
                startActivity(intent);
            }
        });

        tendance_clickable=(TextView)findViewById(R.id.store_tendance);
        tendance_clickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StoreActivity.this,TendanceActivity.class);
                startActivity(intent);
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

        String requeteNouvaute = "http://18.159.181.250/api/documents.php";
        String requeteTendance = "http://18.159.181.250/api/documents.php";
        String requeteGenre = "http://18.159.181.250/api/genre_litteraire.php";
        String requeteType = "http://18.159.181.250/api/types.php";
        String requeteAuteur = "http://18.159.181.250/api/auteur.php";

        new NetworkRecyclerViewDocument(this, StoreActivity.this, R.id.store_nouveauteView,"ListHorizontal").execute(requeteNouvaute);
        new NetworkRecyclerViewDocument(this, StoreActivity.this, R.id.store_tendanceView,"ListHorizontal").execute(requeteTendance);
        new NetworkRecyclerViewCategorie(this,StoreActivity.this, R.id.store_genreView,"genre").execute(requeteGenre);
        new NetworkRecyclerViewCategorie(this,StoreActivity.this, R.id.store_typeView,"type").execute(requeteType);
        new NetworkRecyclerViewCategorie(this,StoreActivity.this, R.id.store_auteurView,"type").execute(requeteAuteur);


    }
    private void initRecyclerView(int i, ArrayList<String> name, ArrayList<String> image ){
        Log.d(TAG,  "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager =  new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL,  false);
        RecyclerView recyclerView = findViewById(i);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( this, name, image, "Product");
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        Navigation.OnclickTopToolbar(v,this);
    }
}