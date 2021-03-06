package fr.sharebookstore.app.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

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
    private ArrayList<String> mPseudo = new ArrayList<>();
    private ArrayList<Integer> mNote = new ArrayList<>();
    private ArrayList<String> mCommentaire = new ArrayList<>();
    private ArrayList<String> mDate = new ArrayList<>();
    private ArrayList<String> mTypeVente = new ArrayList<>();
    private ArrayList<Float> mPrix = new ArrayList<>();
    private ArrayList<Integer> mDuree = new ArrayList<>();


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
        if (myAction.contains("SimpleDocInfo")) {
            GetSimpleDocumentInfo(array);
        }
        else if (myAction.contains("Avis")) {
            GetAvis(array);
        }
        else if (myAction.contains("Vente")) {
            GetVente(array);
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

    private void GetAvis(JSONArray array) {
        for(int i = 0; i < array.length(); i++)
        {
            try {
                JSONObject object = array.getJSONObject(i);
                mPseudo.add(object.getString("Pseudo"));
                mNote.add(object.getInt("note"));
                mCommentaire.add(object.getString("commentaire"));
                mDate.add(object.getString("Date"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        SetAvis();
    }

    private void SetAvis() {
        androidx.recyclerview.widget.RecyclerView myrv = (RecyclerView) myActivity.findViewById(R.id.Product_Avis);
        RecyclerViewAvis myAdapter = new RecyclerViewAvis(myContext,mPseudo,mNote,mCommentaire,mDate);
        myrv.setLayoutManager(new GridLayoutManager(myContext,1));
        myrv.setAdapter(myAdapter);
    }

    private void GetVente(JSONArray array)
    {
        for(int i = 0; i < array.length(); i++)
        {
            try {
                JSONObject object = array.getJSONObject(i);
                mTypeVente.add(object.getString("Type_de_vente"));
                mPrix.add(Float.parseFloat(object.getString("Prix")));
                mDuree.add(object.getInt("Duree"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        SetVente();
    }

    private void SetVente() {
        androidx.recyclerview.widget.RecyclerView myrv = (RecyclerView) myActivity.findViewById(R.id.Product_Vente);
        RecyclerViewVente myAdapter = new RecyclerViewVente(myContext,mTypeVente,mPrix,mDuree);
        myrv.setLayoutManager(new GridLayoutManager(myContext,1));
        myrv.setAdapter(myAdapter);
    }
}
