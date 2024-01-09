package com.example.blood_bank_management.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.blood_bank_management.R;
import com.google.firebase.database.core.Repo;

public class RegistrationActivity extends AppCompatActivity {

    private Button DonarBtn,RepoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        DonarBtn = findViewById(R.id.donBtn);
        RepoBtn = findViewById(R.id.RepoBtn);

        DonarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this,DonarActivity.class);
                startActivity(intent);
            }
        });

        RepoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this,RepoActivity.class);
                startActivity(intent);

            }
        });
    }
}