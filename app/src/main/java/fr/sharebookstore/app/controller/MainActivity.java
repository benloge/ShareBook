package fr.sharebookstore.app.controller;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<String> mImageUrls2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getImages();
    }

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

        initRecyclerView(R.id.recyclerView, mNames, mImageUrls);
        initRecyclerView(R.id.recyclerView2, mNames2, mImageUrls2);

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