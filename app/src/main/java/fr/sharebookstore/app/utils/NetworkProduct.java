package fr.sharebookstore.app.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.sharebookstore.app.R;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class NetworkProduct extends android.os.AsyncTask<String, Void, String> {
    private String myID;
    private Activity myActivity;
    private Context myContext;
    private String myAction;
    private String mTitre;
    private String mImgUrl;
    private String mResume;
    private String mParution;
    private String mLangue;
    private String mAuteur;
    private String mType;
    private String mEditeur;
    private String mCollection;
    private int mPage;


    public NetworkProduct(Context myContext, Activity myActivity, String ID, String Action) {
        this.myActivity = myActivity;
        this.myContext = myContext;
        this.myID = ID;
        this.myAction = Action;
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
        switch (myAction) {
            case "SimpleDocInfo":
                GetSimpleDocumentInfo(array);
        }


    }

    private void GetSimpleDocumentInfo(JSONArray array) {
        for(int i = 0; i < array.length(); i++)
        {
            try {
                JSONObject object = array.getJSONObject(i);
                mTitre = object.getString("Titre");
                mImgUrl = object.getString("Image");
                mResume = object.getString("Resume");
                mParution = object.getString("Date_Parution");
                mPage = object.getInt("Nombre_Pages");
                mLangue = object.getString("Langue");
                mAuteur = object.getString("Auteur_nom");
                mType = object.getString("Types");
                mEditeur = object.getString("Editeur");
                mCollection = object.getString("Collection");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        SetSimpleDocumentInfo();

    }

    private void SetSimpleDocumentInfo() {
        TextView Titre = (TextView) myActivity.findViewById(R.id.Product_Titre);
        ImageView Image = (ImageView) myActivity.findViewById(R.id.Product_Image);
        TextView Auteur = (TextView) myActivity.findViewById(R.id.Product_Auteur);
        TextView Resume = (TextView) myActivity.findViewById(R.id.Product_Resume);
        TextView Parution = (TextView) myActivity.findViewById(R.id.Product_Parution);
        TextView Page = (TextView) myActivity.findViewById(R.id.Product_Page);
        TextView Langue = (TextView) myActivity.findViewById(R.id.Product_Langue);
        Titre.append(mTitre);
        Auteur.append(mAuteur);
        Resume.append(mResume);
        Parution.append(mParution);
        Page.append(String.valueOf(mPage));
        Langue.append(mLangue);
        Glide.with(myContext)
                .asBitmap()
                .load(mImgUrl)
                .into(Image);

    }
}
