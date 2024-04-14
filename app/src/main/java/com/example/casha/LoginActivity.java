package com.example.casha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    // App Components
    TextInputEditText edEmail, edPassword;
    TextInputLayout ledEmail, ledPassword;
    Button loginBtn;
    TextView textViewSignUp;
    private static final String emailSample = "hanz@gmail.com";
    private static final String passwordSample = "admin123";

    // Google Sign Up initializations
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    MaterialButton googleLoginBttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Google
        googleLoginBttn = findViewById(R.id.googleLoginButton);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        googleLoginBttn.setOnClickListener(v -> signIn());

        //Login
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        ledEmail = findViewById(R.id.ledEmail);
        ledPassword = findViewById (R.id.ledPassword);
        loginBtn = findViewById(R.id.loginBtn);
        textViewSignUp = findViewById(R.id.tvSignUp);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sampleLogin();
            }
        });

        //SignUp
        textViewSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    //Sample Login
    void sampleLogin() {
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            ledEmail.setError("Please enter your email");
            return;
        }

        if (TextUtils.isEmpty(password)){
            ledPassword.setError("Please enter your password");
            return;
        }

        if (!email.equals(emailSample) || !password.equals(passwordSample)) {
            // Invalid credentials
            edPassword.setText("");
            Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // This is for Google Sign Up
    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToWelcomeActivity();
            } catch (ApiException e){
                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void navigateToWelcomeActivity(){
        finish();
        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }
}
