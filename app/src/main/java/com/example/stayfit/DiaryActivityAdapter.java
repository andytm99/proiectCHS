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
import java.util.List;

public class DiaryActivityAdapter extends ArrayAdapter<FoodDiary> {
    private Context mContext;
    int mResource;
    public DiaryActivityAdapter(@NonNull Context context, int resource, @NonNull ArrayList<FoodDiary> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String email=getItem(position).getEmail();
        String day=getItem(position).getDay();
        String month=getItem(position).getMonth();
        String year=getItem(position).getYear();
        String cantitate=getItem(position).getCantitate();
        String foodName=getItem(position).getFoodName();
        String category=getItem(position).getCategory();
        String brandName=getItem(position).getBrandName();
        String calories=getItem(position).getCalories();
        String carbs=getItem(position).getCarbs();
        String fats=getItem(position).getFats();
        String proteins=getItem(position).getProteins();
        String barcode=getItem(position).getBarcode();
        FoodDiary foodDiary=new FoodDiary(email,day,month,year,cantitate,foodName,category,brandName,calories,carbs,fats,proteins,barcode);

        float fCalorii=Float.parseFloat(calories);
        String strCalorii=String.valueOf((int)fCalorii);
        float fCarbo=Float.parseFloat(carbs);
        String strCarbo=String.valueOf((int)fCarbo);
        float fGrasimi=Float.parseFloat(fats);
        String strGrasimi=String.valueOf((int)fGrasimi);
        float fProteine=Float.parseFloat(proteins);
        String strProteine= String.valueOf((int)fProteine);
        float fCantitate=Float.parseFloat(cantitate);
        String strCantitate=String.valueOf((int)fCantitate);
        String strMesaj="KCal: "+strCalorii+"\n Carbs: "+strCarbo+" Fats: "+strGrasimi+" Proteins: "+strProteine+" Quantity: "+strCantitate+"g";

        LayoutInflater inflater =LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);
        TextView tvName=(TextView)convertView.findViewById(R.id.diaryNameOfProduct);
        TextView tvCalories=(TextView)convertView.findViewById(R.id.diaryCaloriesOfProduct);

        tvName.setText(foodName);
        tvCalories.setText(strMesaj);
        return convertView;
    }
}
