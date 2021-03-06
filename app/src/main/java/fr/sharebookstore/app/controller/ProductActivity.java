package fr.sharebookstore.app.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Point;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.utils.Navigation;
import fr.sharebookstore.app.utils.NetworkProduct;
import fr.sharebookstore.app.utils.NetworkRecyclerViewDocument;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    private Random mRandom = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Navigation.setBottomNavigation(ProductActivity.this,this,R.id.action_store);
        Navigation.SetTopToolbar(ProductActivity.this, this);

        //TextView Text = (TextView) findViewById(R.id.Product_Auteur);
        //Point size = new Point(Text.getWidth(),Text.getHeight());
        //int[] colors = new int[2];
        //for (int i=0; i<2;i++){
        //    colors[i]= Color.HSVToColor(255,new float[]{mRandom.nextInt(361),1.0f,1.0f});
       // }
       // LinearGradient gradient = new LinearGradient(0,0,size.x,size.y,Color.HSVToColor(255,new float[]{mRandom.nextInt(361),1.0f,1.0f}),Color.HSVToColor(255,new float[]{mRandom.nextInt(361),1.0f,1.0f}),Shader.TileMode.REPEAT);
        //Shader shade = gradient;
        //Text.getPaint().setShader(shade);

        GetInformation();

    }

    private void GetInformation() {
        String Id = getIntent().getStringExtra("EXTRA_SESSION_ID");
        String Name = getIntent().getStringExtra("EXTRA_SESSION_NAME");
        String requeteSimpleDocInfo = "http://18.159.181.250/api/documents.php?id="+Id;
        String requeteAvis = "http://18.159.181.250/api/documents.php?action=avis&id="+Id;
        String requeteVente = "http://18.159.181.250/api/documents.php?action=vente&id="+Id;
        new NetworkProduct(this, ProductActivity.this, Id,"SimpleDocInfo").execute(requeteSimpleDocInfo);
        new NetworkProduct(this,ProductActivity.this,Id,"Vente").execute(requeteVente);
        new NetworkProduct(this, ProductActivity.this, Id,"Avis").execute(requeteAvis);

    }



    @Override
    public void onClick(View v) {
        Navigation.OnclickTopToolbar(v,this);
    }
}