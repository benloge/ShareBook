package fr.sharebookstore.app.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import fr.sharebookstore.app.controller.CategorieActivity;
import fr.sharebookstore.app.controller.CompteActivity;

public class NetworkUpdateAccount extends android.os.AsyncTask<String, Void, String> {

    private int status;
    private Context mContext;


    public interface Listeners {
        void onPreExecute();
        void doInBackground();
        void onPostExecute(String success);
    }

    public NetworkUpdateAccount(Context context){
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.e("TAG", "NetworkAsyncTask is started.");
    }

    @Override
    protected String doInBackground(String... url) {
        Log.e("TAG", "NetworkAsyncTask doing some big work...");
        return MyHttpURLConnection.startHttpRequest(url[0]);
    }

    @Override
    protected void onPostExecute(String success) {
        super.onPostExecute(success);
        Log.e("TAG", "NetworkAsyncTask is finished.");


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
                status=(object.getInt("status"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (status==1)
        {
            Toast.makeText(mContext, "Votre mot de passe a bien été modifié", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mContext, CompteActivity.class);
            mContext.startActivity(intent);
        }
        else
        {
            Toast.makeText(mContext, "Erreur lors du changement de mot de passe", Toast.LENGTH_LONG).show();
        }

    }


}
