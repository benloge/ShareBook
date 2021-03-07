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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.controller.BookViewActivity;
import fr.sharebookstore.app.controller.ProductActivity;
import fr.sharebookstore.app.model.Book;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class RecyclerViewVente extends RecyclerView.Adapter<RecyclerViewVente.ViewHolder>{
    private static final String TAG ="RecyclerViewVente";

    //vars
    private Context mContext;
    private ArrayList<String> mType = new ArrayList<>();
    private ArrayList<Float> mPrix = new ArrayList<>();
    private ArrayList<Integer> mDuree = new ArrayList<>();
    private ArrayList<Integer> mId = new ArrayList<>();
    private ArrayList<String> mTitre = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();

    private SharedPreferences mPreferences;

    public static final String PREF_KEY_OBJECT_BOOK = "PREF_KEY_OBJECT_BOOK";

    public RecyclerViewVente(Context context, ArrayList<String> Type, ArrayList<Float> Prix, ArrayList<Integer> Duree, ArrayList<Integer> ID, ArrayList<String> Titre, ArrayList<String> Image) {
        mContext = context;
        mType = Type;
        mPrix = Prix;
        mDuree = Duree;
        mId = ID;
        mTitre = Titre;
        mImage = Image;
        mPreferences = context.getSharedPreferences("panier", context.MODE_PRIVATE);
    }

    @Override
    public RecyclerViewVente.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,  "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_button_vente,parent,  false);
        return new RecyclerViewVente.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewVente.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        String buttontext = GetButtonText(position);

        holder.button.setText(buttontext);

        int setcolorbutton = position % 9;

        switch (setcolorbutton) {
            case 0:
                holder.button.setBackground(mContext.getDrawable(R.drawable.colorblue));
                break;
            case 1:
                holder.button.setBackground(mContext.getDrawable(R.drawable.colorpurple));
                break;
            case 2:
                holder.button.setBackground(mContext.getDrawable(R.drawable.colorpink));
                break;
            case 3:
                holder.button.setBackground(mContext.getDrawable(R.drawable.colorred));
                break;
            case 4:
                holder.button.setBackground(mContext.getDrawable(R.drawable.colororange));
                break;
            case 5:
                holder.button.setBackground(mContext.getDrawable(R.drawable.coloryellow));
                break;
            case 6:
                holder.button.setBackground(mContext.getDrawable(R.drawable.colorgreen));
                break;
            case 7:
                holder.button.setBackground(mContext.getDrawable(R.drawable.colordrarkgreen));
                break;
            case 8:
                holder.button.setBackground(mContext.getDrawable(R.drawable.colorlightblue));
                break;
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((mType.get(position)).contains("Vente") || (mType.get(position)).contains("Location")) {
                    SaveVente(position);
                    Toast.makeText(mContext, mTitre.get(position)+" ajouté au panier", Toast.LENGTH_SHORT).show();
                }
                else if (mType.contains("Abonnement")) {
                    Toast.makeText(mContext, "Vous allez bientôt pouvoir profiter de l'abonnement ShareBook, un peu de patience", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mType.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.Vente_Button);
        }
    }

    private String GetButtonText(int position) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        String Text = "";
        if ((mType.get(position)).contains("Vente")) {
            Text = "Acheter pour "+df.format(mPrix.get(position))+"€";
        }
        else if ((mType.get(position)).contains("Location")) {
            Text = "Location de "+mDuree.get(position)+" jours pour "+df.format(mPrix.get(position))+"€";
        }
        else if ((mType.get(position)).contains("Abonnement")) {
            Text = "Lire avec l'abonnement";
        }
        return Text;
    }

    private void SaveVente(int position) {
        Book bookvente = new Book(String.valueOf(mId.get(position)),mTitre.get(position),mImage.get(position),String.valueOf(mPrix.get(position)),mType.get(position),String.valueOf(mDuree.get(position)));
        Gson gson = new Gson();
        String json = gson.toJson(bookvente);

        Set<String> BookSet = mPreferences.getStringSet(PREF_KEY_OBJECT_BOOK,new HashSet<>());
        BookSet.add(json);

        SharedPreferences.Editor edit = mPreferences.edit();

        edit.putStringSet(PREF_KEY_OBJECT_BOOK,BookSet);
        edit.apply();

    }
}
