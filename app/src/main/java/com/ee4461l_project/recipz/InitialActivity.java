package com.ee4461l_project.recipz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InitialActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView errorText;
    private EditText emailField, passwordField;
    private Button loginButton, registerButton;
    private static final String TAG = "InitialActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_initial);

        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        errorText = findViewById(R.id.errorText);

        registerButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        errorText.setText("");
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null) {
//            //user is already logged in, forward them to next activity
//            Intent changeToSearchActivity = new Intent(getApplicationContext(), SearchActivity.class);
//            startActivity(changeToSearchActivity);
//        }
    }

    public void createAccount(String email, String password) {
        if(email == null || email.equals("") || password == null || password.equals("")) {
            // make some error message appear on the screen
            errorText.setText(R.string.loginError);
            return;
        }
        else {
            // Attempt to create a new user
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail: success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        // UpdateUI()
                        errorText.setText("");

                        Intent changeToSearchActivity = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(changeToSearchActivity);
                    } else {
                        // If sign in fails, display a message to the user
                        Log.w(TAG, "createUserWithEmail: failure", task.getException());
                        Toast.makeText(InitialActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        // UpdateUI()
                        errorText.setText(R.string.loginError);
                    }
                }
            });
        }
    }

    public void signIn(String email, String password) {
        if(email == null || email.equals("") || password == null || password.equals("")) {
            // make some error message appear on the
            errorText.setText(R.string.loginError);
        }
        else {
            // Attempt to login an existing user
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail: success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        // UpdateUI()
                        errorText.setText("");

                        Intent changeToSearchActivity = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(changeToSearchActivity);
                    } else {
                        // If sign in fails, display a message to the user
                        Log.w(TAG, "createUserWithEmail: failure", task.getException());
                        Toast.makeText(InitialActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        // UpdateUI()
                        errorText.setText(R.string.loginError);
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        if (view == registerButton) {
            //register the user
            createAccount(emailField.getText().toString(), passwordField.getText().toString());
        }
        else if (view == loginButton) {
            signIn(emailField.getText().toString(), passwordField.getText().toString());
        }
    }

}
