package com.example.rent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewRegisteredProperty extends AppCompatActivity {


    RecyclerView recyclerView;
    List<ModelOwnerPropertyList> modelList;
    RecyclerView.LayoutManager layoutManager;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_registered_property);

        //instance of firebasefirestore
        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        modelList = new ArrayList<>();

        showData();
    }

    public void showData(){
        db.collection("Companies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            ModelOwnerPropertyList model = new ModelOwnerPropertyList(document.getString("Address"),
                                    document.getString("CompanyName"),
                                    document.getString("TotalTenants"),
                                    document.getString("ownerName"));
                            modelList.add(model);
                        }
                        MyAdapter adapter = new MyAdapter(ViewRegisteredProperty.this ,modelList);
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "No data collected", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void getData(){

        db.collection("Companies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                ModelOwnerPropertyList model = new ModelOwnerPropertyList(document.getString("Address"),
                                        document.getString("CompanyName"),
                                        document.getString("TotalTenants"),
                                        document.getString("ownerName"));
                                modelList.add(model);
                                }
                            MyAdapter adapter = new MyAdapter(ViewRegisteredProperty.this ,modelList);
                            recyclerView.setAdapter(adapter);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "No data collected", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
