package fr.sharebookstore.app.utils;

import android.content.Context;
import android.content.Intent;
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

import java.text.DecimalFormat;
import java.util.ArrayList;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.controller.BookViewActivity;
import fr.sharebookstore.app.controller.ProductActivity;

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

    public RecyclerViewVente(Context context, ArrayList<String> Type, ArrayList<Float> Prix, ArrayList<Integer> Duree) {
        mContext = context;
        mType = Type;
        mPrix = Prix;
        mDuree = Duree;
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

                Toast.makeText(mContext, mType.get(position), Toast.LENGTH_SHORT).show();
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
}
