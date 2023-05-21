package com.example.quan_ly_thu_chi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quan_ly_thu_chi.databinding.ActivityLoginBinding;
import com.example.quan_ly_thu_chi.databinding.ActivityRegisterBinding;
import com.example.quan_ly_thu_chi.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    protected FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAuth = FirebaseAuth.getInstance();
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final EditText confirmPasswordEditText = binding.confirmPassword;
        final Button loginButton = binding.login;
        final Button registerBtn = binding.register;

        registerBtn.setOnClickListener(view -> {
            if (usernameEditText.getText().toString().isEmpty()) {
                usernameEditText.setError("Not empty");
            }
            if (passwordEditText.getText().toString().isEmpty()) {
                passwordEditText.setError("Not empty");
            }
            if (confirmPasswordEditText.getText().toString().isEmpty()) {
                confirmPasswordEditText.setError("Not empty");
            }
            if (!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())) {
                confirmPasswordEditText.setError("Value not equals password");
            }
            mFirebaseAuth.createUserWithEmailAndPassword(usernameEditText.getText().toString(),passwordEditText.getText().toString()).
                    addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Intent intent = new Intent(RegisterActivity.this,
                                    MainActivity.class);
                            intent.putExtra("email", usernameEditText.getText().toString());
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_LONG).show();
                        }
                    });
        });

        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

}