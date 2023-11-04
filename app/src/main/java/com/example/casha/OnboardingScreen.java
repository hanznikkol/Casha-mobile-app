package com.example.casha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnboardingScreen extends AppCompatActivity {

    private OnboardAdapter onboardAdapter;
    LinearLayout layoutOnboardIndicator;
    MaterialButton onBoardBtn;
    TextView skipBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen);

        setOnboardItems();


        layoutOnboardIndicator = findViewById(R.id.onboardIndicator);
        ViewPager2 onboardViewPager = findViewById(R.id.vpOnboard);
        onboardViewPager.setAdapter(onboardAdapter);

        onBoardBtn = findViewById(R.id.btnNextOn);
        skipBtn = findViewById(R.id.skipBtn);
        setOnboardIndicator();
        setOnboardIndicatorActive(0);

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnboardingScreen.this, LoginActivity.class));
                finish();
            }
        });

        onboardViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setOnboardIndicatorActive(position);
            }
        });

        onBoardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onboardViewPager.getCurrentItem() + 1 < onboardAdapter.getItemCount()){
                    onboardViewPager.setCurrentItem(onboardViewPager.getCurrentItem() + 1);
                }
                else{
                    startActivity(new Intent(OnboardingScreen.this, LoginActivity.class));
                    finish();
                }
            }
        });
    }

    public void setOnboardItems(){
        List<OnboardItem> onboardItems = new ArrayList<>();

        OnboardItem onboard1 = new OnboardItem();
        onboard1.setTitle("Welcome to Casha");
        onboard1.setDescription("Your Personal Finance Companion.");
        onboard1.setImage(R.drawable.logo);

        OnboardItem onboard2 = new OnboardItem();
        onboard2.setTitle("Manage Expenses");
        onboard2.setDescription("Log daily expenses, set budgets, and track your spending effortlessly.");
        onboard2.setImage(R.drawable.manage);

        OnboardItem onboard3 = new OnboardItem();
        onboard3.setTitle("Collaborate on Your Finances");
        onboard3.setDescription("Effortlessly manage shared expenses with loved ones. Easily split bills and budget together.");
        onboard3.setImage(R.drawable.collab);

        OnboardItem onboard4 = new OnboardItem();
        onboard4.setTitle("Let's Get Started");
        onboard4.setDescription("Your financial fitness journey begins now. Start managing your money with Casha.");
        onboard4.setImage(R.drawable.letstart);

        onboardItems.add(onboard1);
        onboardItems.add(onboard2);
        onboardItems.add(onboard3);
        onboardItems.add(onboard4);
        onboardAdapter = new OnboardAdapter(onboardItems);
    }

    private void setOnboardIndicator(){
        ImageView[] indicators = new ImageView[onboardAdapter.getItemCount()];

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(5,0,5,0);

        for (int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.item_indicator_inactive
            ));

            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardIndicator.addView(indicators[i]);
        }
    }

    private void setOnboardIndicatorActive(int index){
        int childCount = layoutOnboardIndicator.getChildCount();
        for (int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView) layoutOnboardIndicator.getChildAt(i);

            if(i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.item_indicator_active)
                );
            }
            else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.item_indicator_inactive));
            }
        }

        if (index == onboardAdapter.getItemCount() - 1){
            onBoardBtn.setText("Start");
        }
        else {
            onBoardBtn.setText("Next");
        }
    }
}