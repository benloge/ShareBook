package fr.sharebookstore.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.controller.BiblioActivity;
import fr.sharebookstore.app.controller.CompteActivity;
import fr.sharebookstore.app.controller.MainActivity;
import fr.sharebookstore.app.controller.PanierActivity;
import fr.sharebookstore.app.controller.StoreActivity;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class Navigation {

    public static void SetTopToolbar(Activity myActivity, View.OnClickListener view) {
        Button buttonCompte = (Button) myActivity.findViewById(R.id.CompteButton);
        buttonCompte.setTag(0);
        buttonCompte.setOnClickListener(view);
    }

    public static void OnclickTopToolbar(View v, Context context) {
        int action = (int) v.getTag();
        switch (action) {
            case 0:
                context.startActivity(new Intent(context, CompteActivity.class));
                break;
        }
    }

    public static void setBottomNavigation(Context mcontext, Activity myActivity, int SelectedItem) {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) myActivity.findViewById(R.id.activity_main_bottom_navigation);
        bottomNavigationView.setSelectedItemId(SelectedItem);
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
                            mcontext.startActivity(new Intent(mcontext, MainActivity.class));

                        }

                        break;
                    case R.id.action_store:
                        if (!classname.contains("StoreActivity")) {
                            mcontext.startActivity(new Intent(mcontext, StoreActivity.class));
                        }

                        break;
                    case R.id.action_biblio:
                        if (!classname.contains("BiblioActivity")) {
                            mcontext.startActivity(new Intent(mcontext, BiblioActivity.class));
                        }
                        break;
                    case R.id.action_panier:
                        if (!classname.contains("PanierActivity")) {
                            mcontext.startActivity(new Intent(mcontext, PanierActivity.class));
                        }
                        break;
                }
                myActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            }
        });
    }
}
