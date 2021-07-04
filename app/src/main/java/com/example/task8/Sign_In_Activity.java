package com.example.task8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task8.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_In_Activity extends AppCompatActivity {


    ActivitySignInBinding binding;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login to your account...");
        progressDialog.setCancelable(false);

        auth = FirebaseAuth.getInstance();

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                if (TextUtils.isEmpty(binding.email.getText().toString()) || TextUtils.isEmpty(binding.password.getText().toString())) {
                    progressDialog.dismiss();
                    Toast.makeText(Sign_In_Activity.this, "Enter All Detail", Toast.LENGTH_SHORT).show();
                } else if (!binding.email.getText().toString().matches(emailPattern)) {
                    progressDialog.dismiss();
                    binding.email.setError("Invalid Email");
                    Toast.makeText(Sign_In_Activity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                } else if (binding.password.getText().toString().length() < 6) {
                    progressDialog.dismiss();
                    binding.password.setError("Invalid Password");
                    Toast.makeText(Sign_In_Activity.this, "Password length is too short", Toast.LENGTH_SHORT).show();
                } else {

                    //Authentication

                    auth.signInWithEmailAndPassword(binding.email.getText().toString(), binding.password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(Sign_In_Activity.this, MainActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(Sign_In_Activity.this, "select your location", Toast.LENGTH_SHORT).show();
                                        finishAffinity();
                                    } else {
                                        Toast.makeText(Sign_In_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }

            }
        });

        binding.goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_In_Activity.this,Sign_Up_Activity.class);
                startActivity(intent);
            }
        });

        binding.tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sign_In_Activity.this, "Create Your account", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Sign_In_Activity.this,Sign_Up_Activity.class);
                startActivity(intent);
            }
        });
        binding.forgatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sign_In_Activity.this, "Forget password clicked", Toast.LENGTH_SHORT).show();
            }
        });
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_In_Activity.this,Sign_Up_Activity.class);
                startActivity(intent);
            }
        });
    }
}
