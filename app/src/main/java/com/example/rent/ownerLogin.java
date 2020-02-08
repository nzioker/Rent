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

public class ownerLogin extends AppCompatActivity {

    private EditText ownerLogin;
    private EditText ownerPassword;
    private Button ownerLoginBtn;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);

        ownerLogin = findViewById(R.id.owner_login_email);
        ownerPassword = findViewById(R.id.editText2);
        ownerLoginBtn = findViewById(R.id.LoginBtn);

        auth = FirebaseAuth.getInstance();

        ownerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ownerLogin.getText().toString();
                String password = ownerPassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter an email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter a password", Toast.LENGTH_SHORT).show();
                }

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(ownerLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(ownerLogin.this, "Failed to login" + task.getException(), Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(ownerLogin.this, OwnerLanding.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}
