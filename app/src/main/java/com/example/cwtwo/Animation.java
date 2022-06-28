package com.example.cwtwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class Animation extends AppCompatActivity {

    ImageView backgroundImage;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        backgroundImage = findViewById(R.id.ImageView_Animation);
        lottieAnimationView = findViewById(R.id.Lottie_Animation);

        lottieAnimationView.animate().setDuration(1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Animation.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },4700);
    }
}