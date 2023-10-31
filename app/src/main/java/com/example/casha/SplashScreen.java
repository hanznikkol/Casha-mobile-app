package com.example.casha;

import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottieAnimationViewLogo, lottieAnimationViewCasha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        lottieAnimationViewLogo = findViewById(R.id.logoSplashAnimate);
        lottieAnimationViewCasha = findViewById(R.id.cashaSplashAnimate);

        lottieAnimationViewLogo.playAnimation();
        lottieAnimationViewCasha.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}