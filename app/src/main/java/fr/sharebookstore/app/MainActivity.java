package fr.sharebookstore.app;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getImages();
    }

    private void getImages(){
        Log.d(TAG, "IniImageBitmaps : preparing bitmaps.");

        mImageUrls.add("https://img-19.ccm2.net/ppaPB1I48R0LInb9Z8QBoUqXqSQ=/480x335/smart/b829396acc244fd484c5ddcdcb2b08f3/ccmcms-commentcamarche/20494859.jpg");
        mNames.add("Exemple");

        mImageUrls.add("https://img-19.ccm2.net/ppaPB1I48R0LInb9Z8QBoUqXqSQ=/480x335/smart/b829396acc244fd484c5ddcdcb2b08f3/ccmcms-commentcamarche/20494859.jpg");
        mNames.add("Exemple");

        mImageUrls.add("https://img-19.ccm2.net/ppaPB1I48R0LInb9Z8QBoUqXqSQ=/480x335/smart/b829396acc244fd484c5ddcdcb2b08f3/ccmcms-commentcamarche/20494859.jpg");
        mNames.add("Exemple");

        mImageUrls.add("https://img-19.ccm2.net/ppaPB1I48R0LInb9Z8QBoUqXqSQ=/480x335/smart/b829396acc244fd484c5ddcdcb2b08f3/ccmcms-commentcamarche/20494859.jpg");
        mNames.add("Exemple");

        mImageUrls.add("https://img-19.ccm2.net/ppaPB1I48R0LInb9Z8QBoUqXqSQ=/480x335/smart/b829396acc244fd484c5ddcdcb2b08f3/ccmcms-commentcamarche/20494859.jpg");
        mNames.add("Exemple");

        mImageUrls.add("https://img-19.ccm2.net/ppaPB1I48R0LInb9Z8QBoUqXqSQ=/480x335/smart/b829396acc244fd484c5ddcdcb2b08f3/ccmcms-commentcamarche/20494859.jpg");
        mNames.add("Exemple");

        mImageUrls.add("https://img-19.ccm2.net/ppaPB1I48R0LInb9Z8QBoUqXqSQ=/480x335/smart/b829396acc244fd484c5ddcdcb2b08f3/ccmcms-commentcamarche/20494859.jpg");
        mNames.add("Exemple");

        mImageUrls.add("https://img-19.ccm2.net/ppaPB1I48R0LInb9Z8QBoUqXqSQ=/480x335/smart/b829396acc244fd484c5ddcdcb2b08f3/ccmcms-commentcamarche/20494859.jpg");
        mNames.add("Exemple");

        initRecyclerView();

    }
    private void initRecyclerView(){
        Log.d(TAG,  "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager =  new LinearLayoutManager( this, LinearLayoutManager.HORIZONTAL,  false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
    }
}