package fr.sharebookstore.app.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.utils.Navigation;
import fr.sharebookstore.app.utils.NetworkRecyclerViewDocument;

public class CategorieActivity extends AppCompatActivity implements View.OnClickListener {

    private String Type;
    private String Id;
    private String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);

        Navigation.SetTopToolbar(CategorieActivity.this, this);
        Navigation.setBottomNavigation(CategorieActivity.this,this,R.id.action_store);
        getImages();
    }

    private void getImages() {

        String requete = getrequete();
        TextView text = (TextView) findViewById(R.id.Categorie_Text);
        Name = getIntent().getStringExtra("EXTRA_SESSION_NAME");
        text.setText(Name);
        new NetworkRecyclerViewDocument(this, CategorieActivity.this, R.id.categorie_View, "StoreVertical").execute(requete);



    }

    private String getrequete() {

        Type = getIntent().getStringExtra("EXTRA_SESSION_TYPE");
        Id = getIntent().getStringExtra("EXTRA_SESSION_ID");
        String requete = "";

        if (Type.contains("Nouveaute")) {
            return "http://18.159.181.250/api/documents.php?action=allnewest";
        }
        else if (Type.contains("Tendance")) {
            return "http://18.159.181.250/api/documents.php"; }

        else {
            return "http://18.159.181.250/api/documents.php?action=" + Type + "&id=" + Id;
        }

    }

    @Override
    public void onClick(View v) {
        Navigation.OnclickTopToolbar(v, this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}