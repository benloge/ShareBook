package fr.sharebookstore.app.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.RecyclerViewAdapter;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class NetworkRecyclerViewDocument extends android.os.AsyncTask<String, Void, String> {

    private Activity myActivity;
    private Context myContext;
    private int RecyclerView;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    public NetworkRecyclerViewDocument(Context myContext, Activity myActivity, int RecyclerView) {
        this.myActivity = myActivity;
        this.RecyclerView = RecyclerView;
        this.myContext = myContext;
    }

    public interface Listeners {
        void onPreExecute();
        void doInBackground();
        void onPostExecute(String success);
    }

    @Override
    protected String doInBackground(String... url) {
        return MyHttpURLConnection.startHttpRequest(url[0]);
    }

    protected void onPostExecute(String success) {
        JSONArray array = null;
        try {
            array = new JSONArray(success);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < array.length(); i++)
        {
            try {
                JSONObject object = array.getJSONObject(i);
                mImageUrls.add(object.getString("Image"));
                mNames.add(object.getString("Titre"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        initRecyclerView(RecyclerView, mNames, mImageUrls);

    }

    private void initRecyclerView(int i, ArrayList<String> name, ArrayList<String> image ){

        LinearLayoutManager layoutManager =  new LinearLayoutManager( myContext, LinearLayoutManager.HORIZONTAL,  false);
        androidx.recyclerview.widget.RecyclerView recyclerView = myActivity.findViewById(i);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter( myContext, name, image);
        recyclerView.setAdapter(adapter);
    }




}
