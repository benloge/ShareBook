package fr.sharebookstore.app.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.controller.BookViewActivity;
import fr.sharebookstore.app.controller.CompteActivity;
import fr.sharebookstore.app.controller.ProductActivity;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG ="RecyclerViewAdapter";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<Integer> mID = new ArrayList<>();
    private Context mContext;
    private String mType;

    public RecyclerViewAdapter(Context context, ArrayList<String> names, ArrayList<String> imageUrls, ArrayList<Integer> ID, String Type) {
        mNames = names;
        mImageUrls = imageUrls;
        mContext = context;
        mType = Type;
        mID = ID;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,  "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,  false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrls.get(position))
                .into(holder.image);

        holder.titre.setText(mNames.get(position));

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on an image:" +mNames.get(position));
                switch (mType) {
                    case "Lecture":
                        mContext.startActivity(new Intent(mContext, BookViewActivity.class));
                        break;
                    case "Store":
                        Intent intent = new Intent(mContext, ProductActivity.class);
                        intent.putExtra("EXTRA_SESSION_ID", String.valueOf(mID.get(position)));
                        intent.putExtra("EXTRA_SESSION_NAME", mNames.get(position));
                        mContext.startActivity(intent);
                    default:
                        Toast.makeText(mContext, mNames.get(position), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView titre;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_book);
            titre = itemView.findViewById(R.id.titre);
        }
    }

}
