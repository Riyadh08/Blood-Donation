package com.example.blood_bank_management.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.blood_bank_management.MainActivity;
import com.example.blood_bank_management.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(SplashScreen.this,FirebaseAuth.getInstance().getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();

                //if(FirebaseAuth.getInstance().getCurrentUser() == null){
                    startActivity(new Intent(SplashScreen.this,LoginActivity.class));
                    finish();
              //  }
//                else{
//                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
//                    finish();
//                }

            }
        },2000);
    }
}