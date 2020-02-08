package com.example.rent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TenantLogin extends AppCompatActivity {

    private EditText emailLogin;
    private EditText passwordLogin;
    private Button loginBtn;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_login);

        emailLogin = findViewById(R.id.tenant_email);
        passwordLogin = findViewById(R.id.tenant_password);
        loginBtn = findViewById(R.id.tenant_login_btn);

        auth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailLogin.getText().toString();
                String password = passwordLogin.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter an email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter a password", Toast.LENGTH_SHORT).show();
                }

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(TenantLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(TenantLogin.this, "Failed to login" + task.getException(), Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(TenantLogin.this, TenantLanding.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });


    }
}
