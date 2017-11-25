package com.ee4461l_project.recipz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InitialActivity extends AppCompatActivity {
    private EditText emailField, passwordField, usernameField;
    private Button loginButton, registerButton;
    private static final String TAG = "InitialActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_initial);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            //user is already logged in, forward them to next activity
        }
    }

    public void createAccount(String email, String password) {
        if(email == null || email.equals("")) {
            // make some error message appear on the screen
        }
        if(password == null || password.equals("")) {
            // make some error message appear on the screen
        }
        // Attempt to create a new user
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail: success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    // UpdateUI()
                }
                else {
                    // If sign in fails, display a message to the user
                    Log.w(TAG, "createUserWithEmail: failure", task.getException());
                    Toast.makeText(InitialActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    // UpdateUI()
                }
            }
        });
    }

    public void signIn(String email, String password) {
        if(email == null || email.equals("")) {
            // make some error message appear on the screen
        }
        if(password == null || password.equals("")) {
            // make some error message appear on the screen
        }
        // Attempt to login an existing user
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail: success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    // UpdateUI()
                }
                else {
                    // If sign in fails, display a message to the user
                    Log.w(TAG, "createUserWithEmail: failure", task.getException());
                    Toast.makeText(InitialActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    // UpdateUI()
                }
            }
        });
    }

    //TODO: Implement this method stub to change the view of the landing page, showing wether or not the user is logged in
    //private void UpdateUI(String user) {}

}
