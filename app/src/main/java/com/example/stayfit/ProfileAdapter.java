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

public class ProfileAdapter extends ArrayAdapter<User> {
    private Context mContext;
    int mResource;

    public ProfileAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String email = getItem(position).getEmail();
        String day = getItem(position).getDay();
        String month = getItem(position).getMonth();
        String year = getItem(position).getYear();
        String height = getItem(position).getHeight();
        String weight = getItem(position).getWeight();
        String targetWeight = getItem(position).getTargetWeight();

        LayoutInflater inflater =LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);

        TextView tvEmail=(TextView) convertView.findViewById(R.id.editTextEmail);
        TextView tvDay=(TextView) convertView.findViewById(R.id.spinnerDOBDay);
        TextView tvMonth=(TextView) convertView.findViewById(R.id.spinnerDOBMonth);
        TextView tvYear=(TextView) convertView.findViewById(R.id.spinnerDOBYear);
        TextView tvHeight=(TextView) convertView.findViewById(R.id.editTextHeightCm);
        TextView tvWeight=(TextView) convertView.findViewById(R.id.editTextWeight);
        TextView tvTargetWeight=(TextView) convertView.findViewById(R.id.editTextTargetWeight);

        tvEmail.setText(email);
        tvDay.setText(day);
        tvMonth.setText(month);
        tvYear.setText(year);
        tvHeight.setText(height);
        tvWeight.setText(weight);
        tvTargetWeight.setText(targetWeight);

        return convertView;
    }
}
