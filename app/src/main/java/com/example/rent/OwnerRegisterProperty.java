package com.example.rent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class OwnerRegisterProperty extends AppCompatActivity {
    EditText companyName, ownerName, address, tenancy;
    Button submitBtn;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_register_property);

        db = FirebaseFirestore.getInstance();

        companyName = findViewById(R.id.company_name_owner);
        ownerName = findViewById(R.id.owner_name);
        address = findViewById(R.id.property_address);
        tenancy = findViewById(R.id.no_of_tenants);
        submitBtn= findViewById(R.id.submit_owner_landing);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String company = companyName.getText().toString().trim();
                String owner = ownerName.getText().toString().trim();
                String propertyAddress = address.getText().toString().trim();
                String tenants = tenancy.getText().toString().trim();

                uploadData(company,owner,propertyAddress,tenants);
             }
        });
    }

    public void uploadData(String company, String owner, String propertyAddress,String tenants){
        Map<String, Object> data = new HashMap<>();
        data.put("CompanyName",company);
        data.put("ownerName",owner);
        data.put("Address", propertyAddress);
        data.put("TotalTenants", tenants);

        db.collection("Companies").document("Company")
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Successfull..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(OwnerRegisterProperty.this, OwnerLanding.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
