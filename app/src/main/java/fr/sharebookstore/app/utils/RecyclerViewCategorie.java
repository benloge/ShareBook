package fr.sharebookstore.app.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.controller.BookViewActivity;
import fr.sharebookstore.app.controller.CategorieActivity;
import fr.sharebookstore.app.controller.CompteActivity;
import fr.sharebookstore.app.controller.ProductActivity;


public class RecyclerViewCategorie extends RecyclerView.Adapter<RecyclerViewCategorie.ViewHolder> {

    private static final String TAG ="RecyclerViewAdapter";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mId = new ArrayList<>();
    private Context mContext;
    private String mType;

    public RecyclerViewCategorie(Context context, ArrayList<String> names, ArrayList<Integer> id, String Type) {
        mNames = names;
        mId = id;
        mContext = context;
        mType = Type;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,  "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_button,parent,  false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.button.setText(mNames.get(position));

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
                Log.d(TAG, "onClick: clicked on an image:" +mNames.get(position));
                Intent intent = new Intent(mContext, CategorieActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", String.valueOf(mId.get(position)));
                intent.putExtra("EXTRA_SESSION_NAME", mNames.get(position));
                intent.putExtra("EXTRA_SESSION_TYPE", mType);
                mContext.startActivity(intent);
                Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return mId.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.Categorie_Button);
        }
    }

}