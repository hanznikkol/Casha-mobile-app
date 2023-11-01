package com.example.casha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    ImageButton btnBackButton;
    ProgressBar progressBar;

    // Firebase Initializations
    FirebaseAuth mAuth;

    // Normal Login Initializations
    TextInputEditText signupFullName, signupEmail, signupUsername, signupPassword, signupConfirmPassword;
    MaterialButton signupBtnRegister;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnBackButton = findViewById(R.id.imgBtnBack);
        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        progressBar = findViewById(R.id.progressBar);

        // Firebase stuff
        mAuth = FirebaseAuth.getInstance();

        // Normal Login
        signupFullName = findViewById(R.id.edFullName);
        signupEmail = findViewById(R.id.edEmail);
        signupUsername = findViewById(R.id.edEmailLogin);
        signupPassword = findViewById(R.id.edPassword);
        signupConfirmPassword = findViewById(R.id.edConfirmPassword);
        signupBtnRegister = findViewById(R.id.registerBtn);

        signupBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String fullName, email, username, password, confirmPassword;
                fullName = String.valueOf(signupFullName.getText());
                email = String.valueOf(signupEmail.getText());
                username = String.valueOf(signupUsername.getText());
                password = String.valueOf(signupPassword.getText());
                confirmPassword = String.valueOf(signupConfirmPassword.getText());

                // Toasts if fields are empty
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(SignUpActivity.this, "Enter email.", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(fullName)){
                    Toast.makeText(SignUpActivity.this, "Enter full name.", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(username)){
                    Toast.makeText(SignUpActivity.this, "Enter username.", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(SignUpActivity.this, "Enter password.", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(SignUpActivity.this, "Enter your password again.", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                //Toasts if the password and confirm password does not match.
                if (!password.equals(confirmPassword)){
                    Toast.makeText(SignUpActivity.this, "Your passwords does not match.", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(SignUpActivity.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}