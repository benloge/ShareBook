package fr.sharebookstore.app.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.controller.BookViewActivity;
import fr.sharebookstore.app.controller.ProductActivity;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class RecyclerViewAvis extends RecyclerView.Adapter<RecyclerViewAvis.ViewHolder>{
    private static final String TAG ="RecyclerViewAvis";

    //vars
    private Context mContext;
    private String mType;
    private ArrayList<String> mPseudo = new ArrayList<>();
    private ArrayList<Integer> mNote = new ArrayList<>();
    private ArrayList<String> mCommentaire = new ArrayList<>();
    private ArrayList<String> mDate = new ArrayList<>();

    public RecyclerViewAvis(Context context, ArrayList<String> Pseudo, ArrayList<Integer> Note, ArrayList<String> Commentaire, ArrayList<String> Date) {
        mContext = context;
        mPseudo = Pseudo;
        mNote = Note;
        mCommentaire = Commentaire;
        mDate = Date;
    }

    @Override
    public RecyclerViewAvis.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,  "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_avis,parent,  false);
        return new RecyclerViewAvis.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAvis.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.textPseudo.setText(mPseudo.get(position));
        holder.textCommentaire.setText(mCommentaire.get(position));
        holder.textDate.setText(mDate.get(position));
        holder.rating.setRating(mNote.get(position));

    }

    @Override
    public int getItemCount() {
        return mPseudo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textPseudo;
        TextView textCommentaire;
        TextView textDate;
        RatingBar rating;

        public ViewHolder(View itemView) {
            super(itemView);
            textPseudo = itemView.findViewById(R.id.avis_pseudo);
            textCommentaire = itemView.findViewById(R.id.avis_commentaire);
            textDate = itemView.findViewById(R.id.avis_date);
            rating = itemView.findViewById(R.id.avis_rating);
        }
    }
}
