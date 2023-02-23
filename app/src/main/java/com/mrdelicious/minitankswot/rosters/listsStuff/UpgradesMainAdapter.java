package com.mrdelicious.minitankswot.rosters.listsStuff;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.mrdelicious.minitankswot.App;
import com.mrdelicious.minitankswot.EverythingDatabase;
import com.mrdelicious.minitankswot.R;
import com.mrdelicious.minitankswot.rosters.ChoseUpgradeActivity;

import java.util.List;

public class UpgradesMainAdapter extends RecyclerView.Adapter<UpgradesMainAdapter.UpgradesMainViewHolder> {
    List<UpgradesMain> upgradesList;
    String typUlepszenia;
    EverythingDatabase db;
    Context context;

    public UpgradesMainAdapter(List<UpgradesMain> upgradesList, Context context, String typUlepszenia) {
        this.upgradesList = upgradesList;
        this.context = context;
        this.typUlepszenia = typUlepszenia;
        db = App.getDB(context);
    }

    @NonNull
    @Override
    public UpgradesMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_roster_upgrade, parent, false);
        return new UpgradesMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpgradesMainViewHolder holder, int position) {
        holder.name.setText(upgradesList.get(position).getName());
        holder.pts.setText(upgradesList.get(position).getPts());
        holder.btn.setOnClickListener(view -> {
            Intent intent = new Intent(context, ChoseUpgradeActivity.class);
            intent.putExtra("nazwa", upgradesList.get(position).getName());
            intent.putExtra("typ", typUlepszenia);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return upgradesList.size();
    }

    public static class UpgradesMainViewHolder extends RecyclerView.ViewHolder {
        TextView name, pts;
        RadioButton btn;
        ConstraintLayout parentLayout;

        public UpgradesMainViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.layoutUpgrade_name);
            pts = itemView.findViewById(R.id.layoutUpgrade_pkt);
            btn = itemView.findViewById(R.id.layoutUpgrade_btn);
            parentLayout = itemView.findViewById(R.id.upgradeAddLayout);
        }
    }
}
