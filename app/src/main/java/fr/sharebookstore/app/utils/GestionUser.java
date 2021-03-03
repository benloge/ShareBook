package fr.sharebookstore.app.utils;

import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.model.User;

/**
 * Created by Benjamin on 02/03/2021.
 */
public class GestionUser implements NetworkAsyncTask.Listeners{

    public User user = new User();
    private String pwd;


    public void GetUser(String pseudo, String password) {
        pwd = password;
        executeUserCheckRequest("http://18.159.181.250/api/utilisateur.php?action=getuser&pseudo="+pseudo+"&mdp="+password);
        return ;
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
                    user.setmId(object.getInt("ID_Utilisateur"));
                    user.setmPseudo(object.getString("Pseudo"));
                    user.setmNom(object.getString("Nom"));
                    user.setmPrenom(object.getString("Prenom"));
                    user.setmemail(object.getString("Email"));
                    user.setmTel(object.getString("Tel"));
                    user.setmGenre(object.getInt("Genre"));
                    user.setmMdp(pwd);
                    user.setmStatus(Boolean.TRUE);

                    //mImageUrls.add(object.getString("Image"));
                    //mNames.add(object.getString("Titre"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
