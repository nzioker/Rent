package com.example.rent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button tenant;
    private Button owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tenant = findViewById(R.id.tenant_button);
        owner = findViewById(R.id.owner_btn);

        tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TenantSignUp.class);
                startActivity(intent);
            }
        });

        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OwnerSignUp.class);
                startActivity(intent);
            }
        });
    }
}
