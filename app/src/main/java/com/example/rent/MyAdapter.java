package com.example.rent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    ViewRegisteredProperty viewProperty;

    List<ModelOwnerPropertyList> modelList;
    FirebaseFirestore db;

    public MyAdapter(ViewRegisteredProperty viewProperty, List<ModelOwnerPropertyList> modelList) {
        this.viewProperty = viewProperty;
        this.modelList = modelList = new ArrayList<>();//No error, but it doesnt display the list. Blankpage

        db = FirebaseFirestore.getInstance();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView AddressV, CompanyNameV, TotalTenantsV, ownerNameV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            AddressV = itemView.findViewById(R.id.Address);
            CompanyNameV = itemView.findViewById(R.id.companyName);
            TotalTenantsV = itemView.findViewById(R.id.TotalTenants);
            ownerNameV = itemView.findViewById(R.id.ownerName);
        }


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.registered_property_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.AddressV.setText(modelList.get(position).getAddress());
            holder.CompanyNameV.setText(modelList.get(position).getCompanyName());
            holder.TotalTenantsV.setText(modelList.get(position).getTotalTenants());
            holder.ownerNameV.setText(modelList.get(position).getOwnerName());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }



}
