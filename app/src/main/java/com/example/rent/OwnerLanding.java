package com.example.rent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OwnerLanding extends AppCompatActivity {

    TextView registerProperty,  viewRegisteredProperty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_landing);

        registerProperty = findViewById(R.id.registerProperty);
        viewRegisteredProperty = findViewById(R.id.viewRegisteredProperty);

        registerProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OwnerRegisterProperty.class);
                startActivity(intent);
            }
        });
        viewRegisteredProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewRegisteredProperty.class);
                startActivity(intent);
            }
        });
    }
}
