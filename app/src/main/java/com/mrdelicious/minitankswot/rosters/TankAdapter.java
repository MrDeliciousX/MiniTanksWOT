package com.mrdelicious.minitankswot.rosters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mrdelicious.minitankswot.R;

import java.util.List;
import java.util.Locale;

public class TankAdapter extends ArrayAdapter<TankOnList> {

    public TankAdapter(Context context, List<TankOnList> tankOnLists) {
        super(context, R.layout.add_tank_item_layout, tankOnLists);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TankOnList tank = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.add_tank_item_layout, parent, false);
        }

        TextView name = convertView.findViewById(R.id.addTankLayout_name);
        TextView cost = convertView.findViewById(R.id.addTankLayout_pts);
        ImageView nation = convertView.findViewById(R.id.addTankLayout_nation);

        name.setText(tank.name);
        cost.setText(tank.cost);
        nation.setImageResource(tank.nation);

        return super.getView(position, convertView, parent);
    }
}