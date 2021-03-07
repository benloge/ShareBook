package fr.sharebookstore.app.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.paypal.android.sdk.e;
import com.paypal.android.sdk.payments.PayPalAuthorization;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalFuturePaymentActivity;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.utils.Navigation;
import fr.sharebookstore.app.utils.RecyclerViewPanier;
import fr.sharebookstore.app.utils.RecyclerViewVente;

public class PanierActivity extends AppCompatActivity  implements View.OnClickListener{

    private static final String TAG = "PanierActivity";

    private SharedPreferences mPreferencesPanier;
    public static final String PREF_KEY_OBJECT_BOOK = "PREF_KEY_OBJECT_BOOK";



    private static final String PAYPAL_KEY = "AVIlTmefnj1FramnkA8mdYED2aHmXLFm0NoRAzxd4zAx_0zdj831XD_cGNQ4lUOEy6L1i9ujx0CySA7Z";
    private static final int REQUEST_CODE_PAYMENT = 1;
    private static final int REQUEST_CODE_FUTURE_PAYMENT = 2;
    private static final String CONFIG_ENVIRONNEMENT = PayPalConfiguration.ENVIRONMENT_SANDBOX;
    PayPalPayment thingsToBuy;

    private static final int PAYPAL_REQUEST_CODE = 7777;
    private static PayPalConfiguration config = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(PAYPAL_KEY);
    String amount = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);
        Navigation.setBottomNavigation(PanierActivity.this,this,R.id.action_panier);
        Navigation.SetTopToolbar(PanierActivity.this, this);
        mPreferencesPanier = getSharedPreferences("panier", MODE_PRIVATE);
        //start paypal service
        Intent intent = new Intent(this,PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);
        SetPanier();
        SetPayer();
    }


    private void setBottomNavigation() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_panier);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected() {
                return onNavigationItemSelected();
            }

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String classname = this.getClass().getName();
                switch (item.getItemId()) {
                    case R.id.action_accueil:
                        if (!classname.contains("MainActivity")) {
                            startActivity(new Intent(PanierActivity.this, MainActivity.class));
                        }

                        break;
                    case R.id.action_store:
                        if (!classname.contains("StoreActivity")) {
                            // startActivity(new Intent(PanierActivity.this, StoreActivity.class));
                        }

                        break;
                    case R.id.action_biblio:
                        if (!classname.contains("BiblioActivity")) {
                            startActivity(new Intent(PanierActivity.this, BiblioActivity.class));
                        }
                        break;
                    case R.id.action_panier:
                        if (!classname.contains("PanierActivity")) {
                            startActivity(new Intent(PanierActivity.this, PanierActivity.class));
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        Navigation.OnclickTopToolbar(v,this);
    }

    private void SetPanier() {
        androidx.recyclerview.widget.RecyclerView myrv = (RecyclerView) findViewById(R.id.Panier_View);
        RecyclerViewPanier myAdapter = new RecyclerViewPanier(PanierActivity.this,this);
        myrv.setLayoutManager(new GridLayoutManager(PanierActivity.this,1));
        myrv.setAdapter(myAdapter);
    }

    private void SetPayer() {
        ArrayList<String> BootArray = new ArrayList<>(mPreferencesPanier.getStringSet(PREF_KEY_OBJECT_BOOK, new HashSet<String>()));
        int i = 0;
        float prixttc = 0;
        float prixht = 0;
        float prixtva = 0;
        for(i = 0; i < BootArray.size(); i++) {
            try {
                String json = BootArray.get(i);
                JSONObject jsonbook = new JSONObject(json);
                prixttc += Float.valueOf(jsonbook.getString("prix"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        prixht = (float) (prixttc/(1+5.5/100));
        prixtva = prixttc - prixht;
        TextView textprixttc = (TextView) findViewById(R.id.Panier_Total_TTC);
        TextView textprixht = (TextView) findViewById(R.id.Panier_Total_HT);
        TextView textprixtva = (TextView) findViewById(R.id.Panier_Total_TVA);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        textprixttc.setText("Prix Total TTC : "+String.valueOf(df.format(prixttc))+"€");
        textprixht.setText("Prix Total HT : "+String.valueOf(df.format(prixht))+"€");
        textprixtva.setText("Prix Total TVA (5,5%) : "+String.valueOf(df.format(prixtva))+"€");

        Button button = (Button) findViewById(R.id.Panier_Payer);
        TextView text = (TextView) findViewById(R.id.Panier_Text);

        amount = String.valueOf(prixttc);

        if (prixttc == 0) {
            button.setVisibility(View.GONE);
            textprixttc.setVisibility(View.GONE);
            textprixht.setVisibility(View.GONE);
            textprixtva.setVisibility(View.GONE);
            text.setText("Votre panier est tristement vide. Allez le remplir dans le store.");
        }
        else {
            button.setText("Payer "+String.valueOf(df.format(prixttc))+"€ avec Paypal");
            float finalPrixttc = prixttc;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MakePayement();
                }
            });
        }



    }


    private void MakePayement() {
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)),"USD",
                "Purchase Goods",PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        startActivityForResult(intent,PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                if (confirm != null) {
                    Toast.makeText(this, "Le Payement à était accepté avec succès", Toast.LENGTH_LONG).show();
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "Le payement à était annulé", Toast.LENGTH_LONG).show();
                } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                    Toast.makeText(this, "Erreur", Toast.LENGTH_LONG).show();
                }
            }
        }
        else if(requestCode == REQUEST_CODE_FUTURE_PAYMENT) {
            if(resultCode == Activity.RESULT_OK) {
                PayPalAuthorization authorization = data.getParcelableExtra(PayPalFuturePaymentActivity.EXTRA_RESULT_AUTHORIZATION);
                if (authorization != null) {
                    Toast.makeText(this,"Payement futur accepté", Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));
        super.onDestroy();
    }
}