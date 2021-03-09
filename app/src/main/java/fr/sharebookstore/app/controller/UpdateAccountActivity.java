package fr.sharebookstore.app.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.model.User;
import fr.sharebookstore.app.utils.InputValidation;
import fr.sharebookstore.app.utils.Navigation;
import fr.sharebookstore.app.utils.NetworkAsyncTask;
import fr.sharebookstore.app.utils.NetworkProduct;
import fr.sharebookstore.app.utils.NetworkUpdateAccount;

public class UpdateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private InputValidation inputValidation;
    private EditText editMdp;
    private EditText editNewMdp;
    private EditText editConfirmMdp;
    private Button buttonUpdateMdp;
    private TextView MdpErreur;
    private TextView NewMdpErreur;
    private TextView ConfirmMdpErreur;
    private String Password;
    private String NewPassword;
    private String ConfirmPassword;
    private SharedPreferences mPreferences;
    private String Pseudo;

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
        setContentView(R.layout.activity_update_account);
        new NetworkUpdateAccount(UpdateAccountActivity.this).execute();

        editMdp = (EditText) findViewById(R.id.editText_Mdp);
        editNewMdp = (EditText) findViewById(R.id.editText_new_Mdp);
        editConfirmMdp = (EditText) findViewById(R.id.editText_confirm_Mdp);
        MdpErreur = (TextView) findViewById(R.id.Mdp_erreur);
        NewMdpErreur = (TextView) findViewById(R.id.New_Mdp_erreur);
        ConfirmMdpErreur = (TextView) findViewById(R.id.Confirm_Mdp_erreur);
        mPreferences = getSharedPreferences("user",MODE_PRIVATE);
        buttonUpdateMdp = (Button) findViewById(R.id.button_update_mdp);

        initObject();
    }

    private void initObject() {
        inputValidation = new InputValidation(UpdateAccountActivity.this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_update_mdp:
                verifyPassword();
                break;
            default:
        }
        Navigation.SetTopToolbar(UpdateAccountActivity.this, this);
    }

    private void verifyPassword() {
        if (!inputValidation.isInputEditTextFilled(editMdp, MdpErreur, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(editNewMdp, NewMdpErreur, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(editConfirmMdp, ConfirmMdpErreur, getString(R.string.error_message_password))) {
            return;
        }

        Password = editMdp.getText().toString().trim();
        NewPassword = editNewMdp.getText().toString().trim();
        ConfirmPassword = editConfirmMdp.getText().toString().trim();
        Pseudo = mPreferences.getString(PREF_KEY_PSEUDO, null);
        executePasswordCheckRequest("http://18.159.181.250/api/utilisateur.php?action=updatemdp&pseudo="+Pseudo+"&mdp="+Password+"&oldmdp="+Password+"&newmdp="+NewPassword+"&confirmmdp="+ConfirmPassword+"");
    }


    private void executePasswordCheckRequest(String requete){
        new NetworkUpdateAccount(this).execute(requete);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}