package com.example.ajs00.ssmm_1718_practica2_g06;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

import static android.Manifest.permission.READ_CONTACTS;
import static com.example.ajs00.ssmm_1718_practica2_g06.R.layout.activity_main;

/**
 * A login screen that offers login via email/password.
 * Esta es una plantilla prefabricada para realizar un login.
 * se crea con :  File > New > Activity > Login Activity
 * Lo modificaremos para que se adapte a nuestra práctica.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    private UserLoginTask mAuthTask = null;
    private static final String DUMMY_USER_ID = "1111111111";
    private static final String DUMMY_PASSWORD = "12345";
    // UI references.
    private ImageView mImagen;
    private TextInputLayout mTextInputLayout_User;
    private TextInputLayout mTextInputLayout_Contraseña;
    private AutoCompleteTextView mUser_id; //memailview
    private EditText mPassword;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUser_id = (AutoCompleteTextView) findViewById(R.id.user_id);
        mImagen = (ImageView) findViewById(R.id.Imagen);
        mTextInputLayout_User = (TextInputLayout) findViewById(R.id.TextInputLayout_User);
        mTextInputLayout_Contraseña = (TextInputLayout) findViewById(R.id.TextInputLayout_Contraseña);
        Button mLogin_button = (Button) findViewById(R.id.login_button);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        mPassword = (EditText) findViewById(R.id.password);

        mPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    if (!isOnline()) {
                        showLoginError(getString(R.string.error_field_required));
                        return false;
                    }
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        mLogin_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOnline()) {
                    showLoginError(getString(R.string.error_field_required));
                    return;
                }
                attemptLogin();

            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUser_id.setError(null);
        mPassword.setError(null);

        // Store values at the time of the login attempt.
        String user_id = mUser_id.getText().toString();
        String password = mPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPassword.setError(getString(R.string.error_invalid_password));
            focusView = mPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(user_id)) {
            mUser_id.setError(getString(R.string.error_field_required));
            focusView = mUser_id;
            cancel = true;
        } else if (!isUserIdValid(user_id)) {
            mUser_id.setError(getString(R.string.error_invalid_email));
            focusView = mUser_id;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(user_id, password);
            mAuthTask.execute((Void) null);
        }
    }

    //Comprueba si es usuario es válido.
    private boolean isUserIdValid(String user_id) {
        return user_id.length() == 10;
    }

    //Comprueba si la contraseña introducida es válida.
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */

    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginFormView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Object, Object, Integer> {

        private final String mUser_id;
        private final String mPassword;

        UserLoginTask(String user_id, String password) {
            mUser_id = user_id;
            mPassword = password;
        }

        @Override
        protected Integer doInBackground(Object... params) {
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return 4;
            }

            if (!mUser_id.equals(DUMMY_USER_ID)) {
                return 2;
            }

            if (!mPassword.equals(DUMMY_PASSWORD)) {
                return 3;
            }

            return 1;
        }

        @Override
        protected void onPostExecute(final Integer success) {
            showProgress(false);

            switch (success) {
                case 1:
                    showAppointmentsScreen();
                    break;
                case 2:
                    System.out.println("Id user mal");
                case 3:
                    System.out.println("Error de server");
                    break;
                case 4:
                    System.out.println("Error de server");
                    break;
            }
        }
    }

    private void showAppointmentsScreen() {
        startActivity(new Intent(this,Redireccion.class));
    }

    private void showLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    //Comprueba si el servicio está online.
    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

}
