package com.example.ajs00.ssmm_1718_practica2_g06;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Redireccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redireccion);
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
    public String request (String uri) throws IOException {
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

    //Preferencias compartidas
    public void guardaDatos(){
        SharedPreferences sharedPreferences = getPreferences(0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        EditText textoEditText = (EditText)findViewById(R.id.textoEditText);
        editor.putString("texto", textoEditText.getText().toString());
        editor.commit();
    }
    public void LeerDatos(){
        TextView textoTextView = (TextView)findViewById(R.id.textoTextView);
        SharedPreferences sharedPreferences = getPreferences(0);
        textoTextView.setText(sharedPreferences.getString("texto", "no introducido"));
    }

}
