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

public class ViewHistoryListAdapter extends ArrayAdapter<FoodDiary> {
    private Context mContext;
    int mResource;

    public ViewHistoryListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<FoodDiary> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getFoodName();
        String calories = getItem(position).getCalories();

       // Food food=new Food(name,calories);
        LayoutInflater inflater =LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView tvName=(TextView) convertView.findViewById(R.id.nameFoodShowcase);
        TextView tvCalories=(TextView) convertView.findViewById(R.id.caloriesFoodShowcase);

        tvName.setText(name);
        tvCalories.setText(calories);

        return convertView;
    }
}
