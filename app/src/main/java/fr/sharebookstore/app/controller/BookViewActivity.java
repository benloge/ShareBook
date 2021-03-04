package fr.sharebookstore.app.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import fr.sharebookstore.app.R;
import fr.sharebookstore.app.utils.LoadingDialog;
import fr.sharebookstore.app.utils.Navigation;

public class BookViewActivity extends AppCompatActivity implements View.OnClickListener {

    PDFView pdfView;
    LoadingDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);

        Navigation.SetTopToolbar(BookViewActivity.this, this);
        Navigation.setBottomNavigation(BookViewActivity.this,this,R.id.action_biblio);

        pdfView = findViewById(R.id.idPDFView);
        loading = new LoadingDialog(BookViewActivity.this);
        new RetrivePDFfromUrl().execute("https://www.ebooksgratuits.com/pdf/squeeze20.pdf");


    }
    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override

        protected void onPreExecute() {
            loading.startLoadingDialog();
        }

        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfView.fromStream(inputStream).load();
            loading.dismissDialog();
        }
    }
    @Override
    public void onClick(View v) {
        Navigation.OnclickTopToolbar(v, this);
    }
}