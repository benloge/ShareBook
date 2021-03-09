package fr.sharebookstore.app.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.model.User;
import fr.sharebookstore.app.utils.GestionUser;
import fr.sharebookstore.app.utils.InputValidation;
import fr.sharebookstore.app.utils.Navigation;
import fr.sharebookstore.app.utils.NetworkAsyncTask;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, NetworkAsyncTask.Listeners {

    private final AppCompatActivity activity = LoginActivity.this;

    private EditText editPseudo;
    private EditText editMdp;
    private Button buttonlogin;

    private TextView LoginErreur;
    private TextView PseudoErreur;
    private TextView MdpErreur;

    private InputValidation inputValidation;
    public User user;

    private String pseudo;
    private String Password;

    private SharedPreferences mPreferences;

    public static final String PREF_KEY_ID = "PREF_KEY_ID";
    public static final String PREF_KEY_PSEUDO = "PREF_KEY_PSEUDO";
    public static final String PREF_KEY_NOM = "PREF_KEY_NOM";
    public static final String PREF_KEY_PRENOM = "PREF_KEY_PRENOM";
    public static final String PREF_KEY_EMAIL = "PREF_KEY_EMAIL";
    public static final String PREF_KEY_TEL = "PREF_KEY_TEL";
    public static final String PREF_KEY_GENRE = "PREF_KEY_GENRE";
    public static final String PREF_KEY_PASSWORD = "PREF_KEY_PASSWORD";
    public static final String PREF_KEY_STATUS = "PREF_KEY_STATUS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editPseudo = (EditText) findViewById(R.id.editText_Pseudo);
        editMdp = (EditText) findViewById(R.id.editText_Mdp);

        buttonlogin = (Button) findViewById(R.id.button_login);

        LoginErreur = (TextView) findViewById(R.id.Login_erreur);
        PseudoErreur = (TextView) findViewById(R.id.Pseudo_erreur);
        MdpErreur = (TextView) findViewById(R.id.Mdp_erreur);

        mPreferences = getSharedPreferences("user",MODE_PRIVATE);

        Navigation.SetTopToolbar(LoginActivity.this, this);
        Navigation.setBottomNavigation(LoginActivity.this,this,R.id.action_accueil);
        initListeners();
        initObject();
    }

    private void initListeners() {
        buttonlogin.setOnClickListener(this);
    }

    private void initObject() {
        user = new User();
        inputValidation = new InputValidation(activity);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                verifyFromSQLite();
                break;
            default:
        }
        Navigation.SetTopToolbar(LoginActivity.this, this);
    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(editPseudo, PseudoErreur, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(editMdp, MdpErreur, getString(R.string.error_message_email))) {
            return;
        }
        pseudo = editPseudo.getText().toString().trim();
        Password = editMdp.getText().toString().trim();
        executeUserCheckRequest("http://18.159.181.250/api/utilisateur.php?action=getuser&pseudo="+pseudo+"&mdp="+Password);
    }

    private void executeUserCheckRequest(String requete){
        new NetworkAsyncTask(this).execute(requete);
    }

    public void onPreExecute() {
        this.updateUIWhenStartingHTTPRequest();
    }

    public void doInBackground() { }

    public void onPostExecute(String json) {
        this.updateUIWhenStopingHTTPRequest(json);
    }

    // ------------------
    //  UPDATE UI
    // ------------------

    private void updateUIWhenStartingHTTPRequest(){

    }

    private void updateUIWhenStopingHTTPRequest(String response){
        if (!response.contains("[]")) {
            JSONArray array = null;
            try {
                array = new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject object = array.getJSONObject(i);
                    SharedPreferences.Editor editor = mPreferences.edit();
                    editor.putInt(PREF_KEY_ID, object.getInt("ID_Utilisateur"));
                    editor.putString(PREF_KEY_PSEUDO, object.getString("Pseudo"));
                    editor.putString(PREF_KEY_NOM, object.getString("Nom"));
                    editor.putString(PREF_KEY_PRENOM, object.getString("Prenom"));
                    editor.putString(PREF_KEY_EMAIL, object.getString("Email"));
                    editor.putString(PREF_KEY_TEL, object.getString("Tel"));
                    editor.putInt(PREF_KEY_GENRE, object.getInt("Genre"));
                    editor.putString(PREF_KEY_PASSWORD, Password);
                    editor.putBoolean(PREF_KEY_STATUS,Boolean.TRUE);
                    editor.commit();

                    //mImageUrls.add(object.getString("Image"));
                    //mNames.add(object.getString("Titre"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
           // LoginErreur.setText("Bonjour "+mPreferences.getString(PREF_KEY_PRENOM, null));
            startActivity(new Intent(this, MainActivity.class));
            //Intent returnIntent = new Intent();
            //setResult(Activity.RESULT_OK,returnIntent);
            //finish();

        }
        else {
            LoginErreur.setText(R.string.error_valid_email_password);
        }

    }

    public void onPointerCaptureChanged(boolean hasCapture) {

    }



}