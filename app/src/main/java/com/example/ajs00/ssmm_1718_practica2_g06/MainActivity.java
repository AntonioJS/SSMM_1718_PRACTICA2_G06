package com.example.ajs00.ssmm_1718_practica2_g06;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    HttpURLConnection con;
    private static final String LOGTAG = "LogsAndroid";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("myinfotag","algo");
        Log.i("info","con.getInputStream()))");
        super.onCreate(savedInstanceState);
        // Redirección al Login
        if (true) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }
//<>


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return false;
    }

    public String request (String uri) throws IOException{
        StringBuilder sb = new StringBuilder();
        URL url = new URL("http://www4.ujaen.es/~jccuevas/ssmm/autentica.php?user=user&pass=12345");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        ////
        try {

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            //temporary string to hold each line read from the reader
            String inputline;
            while ((inputline = bin.readLine()) != null) {
                sb.append(inputline);
            }
            //////7
        }finally{
            urlConnection.disconnect();
        }


        return sb.toString();
    }
}


