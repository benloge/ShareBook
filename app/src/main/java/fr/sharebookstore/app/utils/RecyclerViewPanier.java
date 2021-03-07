package fr.sharebookstore.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.model.Book;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class RecyclerViewPanier extends RecyclerView.Adapter<RecyclerViewPanier.ViewHolder> {
    private static final String TAG = "RecyclerViewPanier";

    //vars
    private Context mContext;
    private Activity mActivity;

    private SharedPreferences mPreferences;

    public static final String PREF_KEY_OBJECT_BOOK = "PREF_KEY_OBJECT_BOOK";

    public RecyclerViewPanier(Context context, Activity activity) {
        mContext = context;
        mActivity = activity;
        mPreferences = context.getSharedPreferences("panier", context.MODE_PRIVATE);
    }

    @Override
    public RecyclerViewPanier.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_panier, parent, false);
        return new RecyclerViewPanier.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewPanier.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        JSONObject book = GetBookJSON(position);


        holder.titre.setText(GetTitre(book));
        holder.type.setText(GetType(book));
        holder.prix.setText(GetPrix(book));

        Glide.with(mContext)
                .asBitmap()
                .load(GetImage(book))
                .into(holder.image);


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> BookList = new ArrayList<>(mPreferences.getStringSet(PREF_KEY_OBJECT_BOOK, new HashSet<String>()));

                BookList.remove(position);


                Set<String> sBookList = new HashSet<String>(BookList);

                SharedPreferences.Editor edit = mPreferences.edit();

                edit.putStringSet(PREF_KEY_OBJECT_BOOK,sBookList);

                edit.commit();

                Intent intent = mActivity.getIntent();
                mActivity.finish();
                mContext.startActivity(intent);
                mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }

    @Override
    public int getItemCount() {
        Set<String> taille = mPreferences.getStringSet(PREF_KEY_OBJECT_BOOK, new HashSet<String>());
        return taille.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton button;
        ImageView image;
        TextView titre, type, prix;


        public ViewHolder(View itemView) {
            super(itemView);
            button = (ImageButton) itemView.findViewById(R.id.Panier_Delete);
            image = (ImageView) itemView.findViewById(R.id.Panier_Image);
            titre = (TextView) itemView.findViewById(R.id.Panier_titre);
            type = (TextView) itemView.findViewById(R.id.Panier_Type);
            prix = (TextView) itemView.findViewById(R.id.Panier_Prix);
        }
    }

    private String GetTitre(JSONObject book) {
        try {
            return book.getString("titre");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
    private String GetImage(JSONObject book) {
        try {
            return book.getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    private String GetType(JSONObject book) {
        try {
            if ((book.getString("type")).contains("Vente")) {
                return "Achat";
            }
            else if ((book.getString("type")).contains("Location")) {
                return "Location de "+book.getString("duree")+" jours";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String GetPrix(JSONObject book) {
        try {
            return book.getString("prix")+"€";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Book GetBook(int position) {
        ArrayList<String> BootArray = new ArrayList<>(mPreferences.getStringSet(PREF_KEY_OBJECT_BOOK, new HashSet<String>()));
        Gson gson = new Gson();
        String json = BootArray.get(position);
        Book book = gson.fromJson(json,Book.class);
        return book;
        //JSONArray array;
        //array = new JSONArray(BootArray);
        //Book bookvente;
        //try {
        //    JSONObject object = array.getJSONObject(position);
            //bookvente = new Book(object.getString("id"),object.getString("titre"),object.getString("image"),object.getString("prix"),object.getString("type"),object.getString("duree"));
          //  return bookvente;
        //} catch (JSONException e) {
          //  e.printStackTrace();
        //}
        //return new Book("","","","","","");
    }

    private JSONObject GetBookJSON(int position) {
        ArrayList<String> BootArray = new ArrayList<>(mPreferences.getStringSet(PREF_KEY_OBJECT_BOOK, new HashSet<String>()));
        Gson gson = new Gson();
        String json = BootArray.get(position);
        try {
            JSONObject jsonbook = new JSONObject(json);
            return jsonbook;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Book book = gson.fromJson(json, Book.class);
       return null;
    }

}
