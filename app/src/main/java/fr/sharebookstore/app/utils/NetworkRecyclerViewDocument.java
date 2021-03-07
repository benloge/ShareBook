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

import fr.sharebookstore.app.R;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class NetworkRecyclerViewDocument extends android.os.AsyncTask<String, Void, String> {

    private Activity myActivity;
    private Context myContext;
    private int RecyclerView;
    private String viewType;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Integer> mID = new ArrayList<>();

    public NetworkRecyclerViewDocument(Context myContext, Activity myActivity, int RecyclerView, String viewtype) {
        this.myActivity = myActivity;
        this.RecyclerView = RecyclerView;
        this.myContext = myContext;
        this.viewType = viewtype;
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
                mID.add(object.getInt("ID_Document"));
                mImageUrls.add(object.getString("Image"));
                mNames.add(object.getString("Titre"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        initRecyclerView(RecyclerView, mNames, mImageUrls);

    }

    private void initRecyclerView(int i, ArrayList<String> name, ArrayList<String> image ){


        if (viewType == "ListVertical") {
            androidx.recyclerview.widget.RecyclerView myrv = (RecyclerView) myActivity.findViewById(i);
            RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(myContext,name,image,mID,"Lecture");
            myrv.setLayoutManager(new GridLayoutManager(myContext,3));
            myrv.setAdapter(myAdapter);
        }
        else if (viewType == "StoreVertical") {
            androidx.recyclerview.widget.RecyclerView myrv = (RecyclerView) myActivity.findViewById(i);
            RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(myContext,name,image,mID,"Store");
            myrv.setLayoutManager(new GridLayoutManager(myContext,3));
            myrv.setAdapter(myAdapter);
        }
        else if (viewType == "ListHorizontal") {
            LinearLayoutManager layoutManager =  new LinearLayoutManager( myContext, LinearLayoutManager.HORIZONTAL,  false);
            androidx.recyclerview.widget.RecyclerView recyclerView = myActivity.findViewById(i);
            recyclerView.setLayoutManager(layoutManager);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter( myContext, name, image,mID,"Store");
            recyclerView.setAdapter(adapter);
        }
    }




}
