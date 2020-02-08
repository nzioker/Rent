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

public class OwnerSignUp extends AppCompatActivity {

    private EditText ownerName;
    private EditText ownerEmail;
    private EditText ownerPassword;
    private Button ownerSignUp;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_sign_up);

        ownerName= findViewById(R.id.ownerName);
        ownerEmail = findViewById(R.id.editText3);
        ownerPassword = findViewById(R.id.OwnerPassword);
        ownerSignUp = findViewById(R.id.signUp);

        //get firebaseAuth instance

        auth = FirebaseAuth.getInstance();

        ownerSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = ownerName.getText().toString();
                String email = ownerEmail.getText().toString();
                String password = ownerPassword.getText().toString();

                if(TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(),"Enter a username",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter an email address",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter a password",Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(OwnerSignUp.this, "SignUp not successful" + task.getException(), Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(OwnerSignUp.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(OwnerSignUp.this, ownerLogin.class);
                            startActivity(intent);
                        }

                    }
                });
            }
        });
    }
}
