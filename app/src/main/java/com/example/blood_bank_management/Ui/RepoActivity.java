package com.example.blood_bank_management.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.blood_bank_management.MainActivity;
import com.example.blood_bank_management.R;
import com.example.blood_bank_management.databinding.ActivityDonarBinding;
import com.example.blood_bank_management.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RepoActivity extends AppCompatActivity {

    private ActivityDonarBinding binding;
    private String name,phone,email,password;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDonarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        binding.SignUpId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.InputNameId.getText().toString();
                phone = binding.InputPhnId.getText().toString();
                email = binding.InputEmailId.getText().toString();
                password = binding.InputPasswordId.getText().toString();

                if(name.isEmpty()){
                    binding.InputNameId.setError("Enter Your Name");
                    binding.InputNameId.requestFocus();
                }
                else if(phone.isEmpty()){
                    binding.InputPhnId.setError("Enter Your Phone");
                    binding.InputPhnId.requestFocus();
                }
                else if(email.isEmpty()){
                    binding.InputEmailId.setError("Enter Your Email");
                    binding.InputEmailId.requestFocus();
                }
                else if(password.isEmpty()){
                    binding.InputPasswordId.setError("Enter Your password");
                    binding.InputPasswordId.requestFocus();
                }
                else{

                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                                databaseReference = FirebaseDatabase.getInstance().getReference("user").child(currentuser);
                                insertData();
                            } else {
                                // Handle authentication failure
                                Toast.makeText(RepoActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }
    private void insertData(){
        UserModel userModel = new UserModel(name,phone,email,password);

        databaseReference.setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(RepoActivity.this, MainActivity.class));
                }
                finish();
                Toast.makeText(RepoActivity.this, "Repo Registration Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}