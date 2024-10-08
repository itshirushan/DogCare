package com.example.dogcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogcare.databinding.ActivityAdminLoginBinding;

public class AdminLoginActivity extends AppCompatActivity {

    ActivityAdminLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.adminUsername.getText().toString();
                String password = binding.adminPassword.getText().toString();
                //admin user name and password
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(AdminLoginActivity.this, "Fields can not be blank", Toast.LENGTH_SHORT).show();
                } else {
                    if (username.equals("admin") && password.equals("admin")) {
                        Toast.makeText(AdminLoginActivity.this, "Admin Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminLoginActivity.this, AdminProfile.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AdminLoginActivity.this, "Invalid Admin Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.backtouser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
