package com.mrdelicious.minitankswot.rosters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.mrdelicious.minitankswot.R;
import java.util.List;

public class TankOnListAdapter extends RecyclerView.Adapter<TankOnListAdapter.TankOnListViewHolder> {
    List<TankOnList> tankList;
    Context context;

    public TankOnListAdapter(List<TankOnList> tankList, Context context) {
        this.tankList = tankList;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return tankList.size();
    }

    public class TankOnListViewHolder extends RecyclerView.ViewHolder  {
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
