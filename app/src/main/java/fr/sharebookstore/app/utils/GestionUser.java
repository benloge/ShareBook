package fr.sharebookstore.app.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.sharebookstore.app.R;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class GestionUser implements NetworkAsyncTask.Listeners{

    private Boolean StatusUser;

    public boolean checkUser(String pseudo, String password) {
        executeUserCheckRequest("http://18.159.181.250/api/utilisateur.php?action=checkuser&pseudo="+pseudo+"&mdp="+password);
        return StatusUser;
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
        if (response == "True") {
            StatusUser = Boolean.TRUE;
        }
        else {
            StatusUser = Boolean.FALSE;
        }

    }

    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
