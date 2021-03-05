package fr.sharebookstore.app.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import fr.sharebookstore.app.R;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class LoadingDialog {
    Activity myActivity;
    AlertDialog mydialog;

    public LoadingDialog(Activity myActivity) {
        this.myActivity = myActivity;
    }

    public void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(myActivity);

        LayoutInflater inflater = myActivity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progress_bar, null));

        mydialog = builder.create();
        mydialog.show();
    }

    public void dismissDialog() {
        mydialog.dismiss();
    }
}
