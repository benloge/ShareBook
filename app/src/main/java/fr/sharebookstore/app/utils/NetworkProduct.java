package fr.sharebookstore.app.utils;

import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class NetworkProduct extends android.os.AsyncTask<String, Void, String> {
    private String myID;
    private Activity myActivity;
    private Context myContext;
    private String myTitre;
    private String myImgUrl;
    private String myParution;
    private String myNombrePage;
    private String myLangue;

    public NetworkProduct(Context myContext, Activity myActivity, String ID) {
        this.myActivity = myActivity;
        this.myContext = myContext;
        this.myID = ID;
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
              //  mID.add(object.getInt("0"));
               // mNames.add(object.getString("Nom"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
