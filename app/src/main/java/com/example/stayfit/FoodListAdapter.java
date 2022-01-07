package com.example.stayfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FoodListAdapter extends ArrayAdapter<FoodShowcase> {
    private Context mContext;
    int mResource;
    public FoodListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<FoodShowcase> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name=getItem(position).getName();
        String brandName=getItem(position).getBrandName();
        String barCode=getItem(position).getBarcode();

        FoodShowcase food=new FoodShowcase(name,brandName,barCode);
        LayoutInflater inflater =LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView tvName=(TextView) convertView.findViewById(R.id.nameFoodShowcase);
        TextView tvBrandName=(TextView)convertView.findViewById(R.id.brandNameFoodShowcase);
        TextView tvBarcode=(TextView)convertView.findViewById(R.id.barcodeFoodShowcase);

        tvName.setText(name);
        tvBrandName.setText(brandName);
        tvBarcode.setText(barCode);
        return convertView;
    }
}
