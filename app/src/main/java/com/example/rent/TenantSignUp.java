package com.example.rent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class TenantSignUp extends AppCompatActivity {

    private EditText tenantUsername;
    private EditText tenantEmail;
    private EditText tenantPassword;
    private Button tenantSignup;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_sign_up);

        tenantUsername = findViewById(R.id.Username_tenant);
        tenantEmail = findViewById(R.id.email_tenant);
        tenantPassword = findViewById(R.id.password_tenant);
        tenantSignup = findViewById(R.id.signup_tenant);

        //get instance of firebaseauth

        auth = FirebaseAuth.getInstance();

        tenantSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = tenantUsername.getText().toString();
                String email = tenantEmail.getText().toString();
                String password = tenantPassword.getText().toString();

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(),"You have not entered a Username",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "You have not entered an email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "You have not entered a password", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(TenantSignUp.this, "SignUp not successful" + task.getException(), Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(TenantSignUp.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(TenantSignUp.this, TenantLogin.class);
                            startActivity(intent);
                        }

                    }
                });
            }
        });
    }
}
