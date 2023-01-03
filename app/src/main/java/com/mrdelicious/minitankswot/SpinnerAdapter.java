package com.mrdelicious.minitankswot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<CustomSpinner> {
    public SpinnerAdapter(@NonNull Context context, ArrayList<CustomSpinner> customList) {
        super(context, 0, customList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.filter_spinner_layout,parent,false);
        }
        CustomSpinner items = getItem(position);
        ImageView spinnerImage = convertView.findViewById(R.id.ivFilterSpinner);
        TextView spinnerName = convertView.findViewById(R.id.tvFilterSpinner);
        if (items != null){
            spinnerImage.setImageResource(items.getSpinnerImage());
            spinnerName.setText(items.getSpinnerText());
        }
        return convertView;
    }
}
