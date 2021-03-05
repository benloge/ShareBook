package fr.sharebookstore.app.utils;

import android.app.Activity;
import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class NetworkRecyclerViewCategorie extends android.os.AsyncTask<String, Void, String> {
    private Activity myActivity;
    private Context myContext;
    private int RecyclerView;
    private String mCategorieType;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mID = new ArrayList<>();

    public NetworkRecyclerViewCategorie(Context myContext, Activity myActivity, int RecyclerView, String CategorieType) {
        this.myActivity = myActivity;
        this.RecyclerView = RecyclerView;
        this.myContext = myContext;
        this.mCategorieType = CategorieType;
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
                mID.add(object.getString("1"));
                mNames.add(object.getString("Nom"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        initRecyclerView(RecyclerView, mNames, mID);

    }

    private void initRecyclerView(int i, ArrayList<String> name, ArrayList<String> id ){


        LinearLayoutManager layoutManager =  new LinearLayoutManager( myContext, LinearLayoutManager.HORIZONTAL,  false);
        androidx.recyclerview.widget.RecyclerView recyclerView = myActivity.findViewById(i);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewCategorie adapter = new RecyclerViewCategorie( myContext, name, id,mCategorieType);
        recyclerView.setAdapter(adapter);

    }

}
