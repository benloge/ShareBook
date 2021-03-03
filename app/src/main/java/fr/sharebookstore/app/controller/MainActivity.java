package fr.sharebookstore.app.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.RecyclerViewAdapter;
import fr.sharebookstore.app.utils.Navigation;
import fr.sharebookstore.app.utils.NetworkAsyncTask;

public class MainActivity extends AppCompatActivity implements NetworkAsyncTask.Listeners, View.OnClickListener {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<String> mImageUrls2 = new ArrayList<>();

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
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences("user",MODE_PRIVATE);
        //mPreferences = getPreferences(MODE_PRIVATE);


        Navigation.SetTopToolbar(MainActivity.this, this);
        setBienvenue();
        setBottomNavigation();

        getImages();
    }

    private void executeHttpRequest(String requete){
        new NetworkAsyncTask(this).execute(requete);
    }

    public void onPreExecute() {
        this.updateUIWhenStartingHTTPRequest();
    }

    public void doInBackground() { }

    public void onPostExecute(String json) {
        this.updateUIWhenStopingHTTPRequest(json);
    }

    // ------------------
    //  UPDATE UI
    // ------------------

    private void updateUIWhenStartingHTTPRequest(){

    }

    private void updateUIWhenStopingHTTPRequest(String response){
        JSONArray array = null;
        try {
            array = new JSONArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0; i < array.length(); i++)
        {
            try {
                JSONObject object = array.getJSONObject(i);
                mImageUrls.add(object.getString("Image"));
                mNames.add(object.getString("Titre"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        initRecyclerView(R.id.nouveauteView, mNames, mImageUrls);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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



    private void getImages(){
        Log.d(TAG, "IniImageBitmaps : preparing bitmaps.");

        this.executeHttpRequest("http://18.159.181.250/api/documents.php");

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

    private void setBienvenue() {
        TextView welcome = (TextView) findViewById(R.id.welcome);
        String prenom = mPreferences.getString(PREF_KEY_PRENOM, null);
        if (prenom != null) {
            welcome.setText("BIENVENUE "+prenom.toUpperCase()+" SUR SHAREBOOK" );
        }
        else {
            welcome.setText("BIENVENUE SUR SHAREBOOK");
        }


    }


    @Override
    public void onClick(View v) {
        Navigation.OnclickTopToolbar(v,this);
    }
}