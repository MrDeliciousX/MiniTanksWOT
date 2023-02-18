package com.mrdelicious.minitankswot.rosters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.tanks.Tank;
import java.util.List;

public class TankOnListAdapter extends RecyclerView.Adapter<TankOnListAdapter.TankOnListViewHolder> {
    List<TankOnList> tankList;
    EverythingDatabase db;
    long rosterID;
    boolean add;
    Context context;

    public TankOnListAdapter(List<TankOnList> tankList, Context context, boolean add, long rosterID) {
        this.tankList = tankList;
        this.context = context;
        this.add = add;
        this.rosterID = rosterID;
        db = App.getDB(context);
    }

    @NonNull
    @Override
    public TankOnListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tank_in_roster, parent, false);
        return new TankOnListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TankOnListViewHolder holder, int position) {
        holder.tankName.setText(tankList.get(position).getName());
        holder.tankPts.setText(String.valueOf(tankList.get(position).getPts()) + "pkt");
        holder.tankFlag.setImageResource(tankList.get(position).getNation());
        holder.tankImage.setImageResource(tankList.get(position).getImage());
        if(add) {
            holder.tankAddBtn.setOnClickListener(view -> {
                Tank tankToRoster = db.tankDao().findByName(tankList.get(position).getName());
                TanksInRosters tanksInRosters = new TanksInRosters();
                tanksInRosters.tankID = tankToRoster.id;
                tanksInRosters.rosterID = rosterID;
                db.tanksInRostersDao().insertNew(tanksInRosters);
                Toast.makeText(context, "dodano " + tankList.get(position).getName(), Toast.LENGTH_SHORT).show();
            });
        } else {
            holder.tankAddBtn.setVisibility(View.INVISIBLE);
            holder.parentLayout.setOnClickListener(view -> {
                Intent intent = new Intent(context, TankInRosterSettingsActivity.class);
                intent.putExtra("id", rosterID);
                intent.putExtra("tankId", tankList.get(position).getTankID());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return tankList.size();
    }

    public static class TankOnListViewHolder extends RecyclerView.ViewHolder  {
        ImageView tankImage;
        ImageView tankFlag;
        TextView tankName;
        TextView tankPts;
        ImageButton tankAddBtn;
        ConstraintLayout parentLayout;

        public TankOnListViewHolder(@NonNull View itemView) {
            super(itemView);

            tankImage = itemView.findViewById(R.id.layoutTank_image);
            tankFlag = itemView.findViewById(R.id.layoutTank_flag);
            tankName = itemView.findViewById(R.id.layoutTank_name);
            tankPts = itemView.findViewById(R.id.layoutTank_pts);
            tankAddBtn = itemView.findViewById(R.id.layoutTank_addBtn);
            parentLayout = itemView.findViewById(R.id.tankAddLayout);
        }
    }
}
