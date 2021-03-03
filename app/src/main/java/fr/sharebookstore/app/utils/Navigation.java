package fr.sharebookstore.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.controller.CompteActivity;

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
}
