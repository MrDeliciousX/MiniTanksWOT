package com.mrdelicious.minitankswot.rosters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.tanks.Tank;

import java.util.List;

public class TankAdapter extends ArrayAdapter<TankOnList> {

    EverythingDatabase db;
    Context context;
    long rosterID;
    boolean add;

    public TankAdapter(Context context, List<TankOnList> tankOnLists, long id, boolean add) {
        super(context, R.layout.add_tank_item_layout, tankOnLists);
        this.context = context;
        this.rosterID = id;
        this.add = add;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TankOnList tank = getItem(position);
        db = App.getDB(context);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.add_tank_item_layout, parent, false);
        }

        TextView name = convertView.findViewById(R.id.addTankLayout_name);
        TextView cost = convertView.findViewById(R.id.addTankLayout_pts);
        ImageView nation = convertView.findViewById(R.id.addTankLayout_nation);
        ImageButton addButton = convertView.findViewById(R.id.addTankLayout_addButton);

        name.setText(tank.name);
        cost.setText(tank.pts);
        nation.setImageResource(tank.nation);

        if (add) {
            addButton.setOnClickListener(view -> {
                Tank tankToRoster = db.tankDao().findByName(tank.name);
                TanksInRosters tanksInRosters = new TanksInRosters();
                tanksInRosters.tankID = tankToRoster.id;
                tanksInRosters.rosterID = rosterID;
                db.tanksInRostersDao().insertNew(tanksInRosters);
                Toast.makeText(context, "dodano " + tank.name, Toast.LENGTH_SHORT).show();
            });
        } else
            addButton.setVisibility(View.INVISIBLE);

        return convertView;
    }
}
